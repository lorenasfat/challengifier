using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.Mappers
{
    public static class PlanningStepMapper
    {
        public static IEnumerable<PlanningStepDto> ToDtos(this IEnumerable<PlanningStep> planningSteps)
        {
            return planningSteps.Select(p => new PlanningStepDto()
            {
                Id = p.PlanningStep_ID,
                ChallengeId = p.Challenge_ID,
                Description = p.Description,
                Name = p.Name,
                TimeUnitId = p.Duration_TimeUnitId,
                TimeUnitNumber = p.Duration_TimeNumber
            });
        }

        public static PlanningStep ToDbEntity(this PlanningStepDto planningStep)
        {
            return new PlanningStep()
            {
                Duration_TimeNumber = planningStep.TimeUnitNumber,
                Challenge_ID = planningStep.ChallengeId,
                Description = planningStep.Description,
                Duration_TimeUnitId = planningStep.TimeUnitId,
                Name = planningStep.Name,
                PlanningStep_ID = planningStep.Id,
            };
        }
    }
}
