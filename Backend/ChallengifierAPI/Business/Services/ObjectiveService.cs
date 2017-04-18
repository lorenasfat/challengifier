using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;

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
            return _unitOfWork.ObjectiveRepository.All().ToDtos();
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

        private void SetUpObjective(Objective dbObjective, ObjectiveDto objective)
        {
            dbObjective.Challenge_ID = objective.ChallengeId;
            dbObjective.Deadline = objective.Deadline;
            dbObjective.Description = objective.Description;
            dbObjective.Expected_Outcome = objective.ExpectedOutcome;
            dbObjective.Name = objective.Name;
        }
    }
}
