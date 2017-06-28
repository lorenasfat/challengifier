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

        public ObjectiveService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
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

        public IEnumerable<ObjectiveDto> GetAllObjectives()
        {
            var objectives = _unitOfWork.ObjectiveRepository.All();
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
            if (objective.Progress == 10 && !objective.ChallengeId.HasValue)
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.Completed;
            else if(objective.Progress == 10)
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.UnderApproval;
            else if(objective.Progress >0 && objective.Progress<10)
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.Ongoing;
            else
                dbObjective.Status_ID = (int)Common.Enums.ObjectiveStatus.NotActive;
        }
    }
}
