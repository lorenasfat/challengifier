﻿using Business.Components.Interface;
using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Business.Services
{
    public class ObjectiveService : IObjectiveService
    {
        private readonly IUnitOfWork _unitOfWork;
        private readonly IUserRankComponent _userRankComponent;

        public ObjectiveService(IUnitOfWork unitOfWork, IUserRankComponent userRankComponent)
        {
            _unitOfWork = unitOfWork;
            _userRankComponent = userRankComponent;
        }
        public void AddObjective(ObjectiveDto objective)
        {
            try
            {
                _unitOfWork.ObjectiveRepository.Create(objective.ToDbEntity());
                _unitOfWork.ObjectiveRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void DeleteObjective(Guid objectiveId)
        {
            try
            {
                var dbObjective = _unitOfWork.ObjectiveRepository.GetById(objectiveId);
                _unitOfWork.ObjectiveRepository.Delete(dbObjective);
                _unitOfWork.ObjectiveRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void Dispose()
        {
            _unitOfWork.Dispose();
        }

        public IEnumerable<ObjectiveDto> GetAllObjectives(string id)
        {
            var objectives = _unitOfWork.ObjectiveRepository.All().Where(o => o.User_ID == id &&
            ((o.Status_ID == (int)Common.Enums.ObjectiveStatus.NotActive) ||
            (o.Status_ID == (int)Common.Enums.ObjectiveStatus.Ongoing)));
            return objectives.ToDtos();
        }

        public ObjectiveDto GetObjectiveById(Guid Id)
        {
            return _unitOfWork.ObjectiveRepository.GetById(Id).ToDto();
        }

        public void UpdateObjective(ObjectiveDto objective)
        {
            try
            {
                var dbObjective = _unitOfWork.ObjectiveRepository.GetById(objective.Id);
                SetUpObjective(dbObjective, objective);

                if ((dbObjective.Challenge_ID != null && dbObjective.Status_ID == (int)Common.Enums.ObjectiveStatus.Reviewed))
                {
                    var systemGrade = _userRankComponent.PersistSystemGradeForObjective(objective.Id);
                    var userGrade = _unitOfWork.UserRatingRepository.All().Where(r => r.Objective_ID == objective.Id).FirstOrDefault().Grade;
                    int grade = Convert.ToInt32(0.6 * userGrade + 0.4 * systemGrade);

                    dbObjective.Rating += grade;

                    _unitOfWork.UserRepository.All().Where(u => u.Id == objective.UserId)
    .FirstOrDefault().Points += Convert.ToInt32(dbObjective.Rating);
                    _unitOfWork.UserRankRepository.Save();
                }
                else if (dbObjective.Challenge_ID == null && dbObjective.Status_ID == (int)Common.Enums.ObjectiveStatus.Completed)
                {
                    var grade = _userRankComponent.PersistSystemGradeForObjective(objective.Id);

                    dbObjective.Rating += grade;

                    _unitOfWork.UserRepository.All().Where(u => u.Id == objective.UserId)
    .FirstOrDefault().Points += Convert.ToInt32(dbObjective.Rating);
                    _unitOfWork.UserRankRepository.Save();
                }

                _unitOfWork.ObjectiveRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public int CountForChallenge(Guid challengeId)
        {
            var nr = _unitOfWork.ObjectiveRepository.All().Where(o => o.Challenge_ID.HasValue && (o.Challenge_ID.Value == challengeId)).Count();
            return nr;
        }

        private void SetUpObjective(Objective dbObjective, ObjectiveDto objective)
        {
            dbObjective.Challenge_ID = objective.ChallengeId;
            dbObjective.Deadline = objective.Deadline;
            dbObjective.Description = objective.Description;
            dbObjective.Expected_Outcome = objective.ExpectedOutcome;
            dbObjective.Name = objective.Name;
            dbObjective.Progress = objective.Progress;
            dbObjective.Start_Date = objective.StartDate;
            dbObjective.End_Date = objective.EndDate;
            if (objective.Status != 0)
            {
                dbObjective.Status_ID = objective.Status;
            }
            if (objective.Progress == 10 && !objective.ChallengeId.HasValue)
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.Completed;
            else if (objective.Progress == 10 || objective.Status == (int)Common.Enums.ObjectiveStatus.Completed)
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.ForReview;
            else if (objective.Progress > 0 && objective.Progress < 10)
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.Ongoing;
            else
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.NotActive;
        }

        public IEnumerable<ObjectiveForReviewDto> GetObjectivesForReview(Guid id)
        {
            var objectives = _unitOfWork.ObjectiveRepository.All().Where(o => o.Status_ID == (int)Common.Enums.ObjectiveStatus.ForReview && o.Challenge_ID == id).ToList();
            return objectives.ToReviewDtos();
        }

        public void AddObjectiveRating(UserRatingDto rating)
        {
            try
            {
                var userRating = rating.ToDbEntity();
                _unitOfWork.UserRatingRepository.Create(userRating);
                _unitOfWork.UserRatingRepository.Save();
                _unitOfWork.Commit();

                int status;
                if (userRating.Grade == 0)
                    status = (int)Common.Enums.ObjectiveStatus.Rejected;
                else
                    status = (int)Common.Enums.ObjectiveStatus.Reviewed;
                var objective = _unitOfWork.ObjectiveRepository.GetById(rating.ObjectiveId);
                objective.Status_ID = status;
                _unitOfWork.ObjectiveRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception ex)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public IEnumerable<ObjectiveHistoryDto> GetHistoryObjectives(string id)
        {
            return _unitOfWork.ObjectiveRepository.All().Where(o => o.User_ID == id && 
            (o.Status_ID != (int)Common.Enums.ObjectiveStatus.Ongoing
            && o.Status_ID != (int)Common.Enums.ObjectiveStatus.ForReview
            && o.Status_ID != (int)Common.Enums.ObjectiveStatus.NotActive)).ToHistoryDtos();
        }
    }
}
