using Business.DTOs;
using System;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IPlanningStepService
    {
        IEnumerable<PlanningStepDto> GetPlanningSteps(Guid challengeId);
        void DeletePlanningStep(Guid planningStepId);
        void AddPlanningStep(PlanningStepDto planningStep);
    }
}
