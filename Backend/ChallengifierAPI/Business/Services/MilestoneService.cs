using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;

namespace Business.Services
{
    public class MilestoneService : IMilestoneService
    {
        private readonly IUnitOfWork _unitOfWork;
        public MilestoneService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public void AddMilestone(MilestoneDto milestone)
        {
            try
            {
                _unitOfWork.MilestoneRepository.Create(milestone.ToDbEntity());
                _unitOfWork.MilestoneRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void DeleteMilestone(Guid id)
        {
            try
            {
                var dbMilestone = _unitOfWork.MilestoneRepository.GetById(id);
                _unitOfWork.MilestoneRepository.Delete(dbMilestone);
                _unitOfWork.MilestoneRepository.Save();
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

        public IEnumerable<Milestone> GetAllMilestones()
        {
            return _unitOfWork.MilestoneRepository.All();
        }

        public Milestone GetMilestoneById(Guid id)
        {
            return _unitOfWork.MilestoneRepository.GetById(id);
        }

        public void UpdateMilestone(MilestoneDto milestone)
        {
            try
            {
                var dbMilestone = _unitOfWork.MilestoneRepository.GetById(milestone.Id);
                SetUpMilestone(milestone, dbMilestone);
                _unitOfWork.MilestoneRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        private void SetUpMilestone(MilestoneDto milestone, Milestone dbMilestone)
        {
            dbMilestone.Name = milestone.Name;
            dbMilestone.Objective_ID = milestone.ObjectiveId;
            dbMilestone.StartDate = milestone.StartDate;
            dbMilestone.Description = milestone.Description;
            dbMilestone.EndDate = milestone.EndDate;
        }
    }
}
