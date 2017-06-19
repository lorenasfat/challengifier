using System.Collections.Generic;
using System.Linq;
using System.Net.NetworkInformation;
using Business.DTOs;
using DataAccess.Entities;

namespace Business.Mappers
{
    public static class MilestoneMapper
    {
        public static Milestone ToDbEntity(this MilestoneDto milestone)
        {
            return new Milestone()
            {
                Milestone_ID = milestone.Id,
                Description = milestone.Description,
                EndDate = milestone.EndDate,
                Name = milestone.Name,
                Objective_ID = milestone.ObjectiveId,
                StartDate = milestone.StartDate,
            };
        }

        public static IEnumerable<MilestoneDto> ToDtos(this IEnumerable<Milestone> milestones)
        {
            var mils = milestones.Select(m => new MilestoneDto()
            {
                StartDate = m.StartDate,
                Id = m.Milestone_ID,
                EndDate = m.EndDate,
                Name = m.Name,
                Description = m.Description,
                ObjectiveId = m.Objective_ID,
                PlanningStepId = m.PlanningStep_ID
            });

            return mils;
        }
    }
}
