using Business.Services.Interfaces;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Business.DTOs;
using Business.Mappers;

namespace Business.Services
{
    public class PlanningStepService : IPlanningStepService
    {
        private readonly IUnitOfWork _unitOfWork;

        public PlanningStepService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public void AddPlanningStep(PlanningStepDto planningStep)
        {
            try
            {
                _unitOfWork.PlanningStepRepository.Create(planningStep.ToDbEntity());
                _unitOfWork.PlanningStepRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void DeletePlanningStep(Guid planningStepId)
        {
            try
            {
                var planningStep = _unitOfWork.PlanningStepRepository.GetById(planningStepId);
                _unitOfWork.PlanningStepRepository.Delete(planningStep);
                _unitOfWork.PlanningStepRepository.Save();
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

        public IEnumerable<PlanningStepDto> GetPlanningSteps(Guid challengeId)
        {
            try
            {
                var planningSteps = _unitOfWork.PlanningStepRepository.All().Where(p=> p.Challenge_ID == challengeId);
                return planningSteps.ToDtos();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }
    }
}
