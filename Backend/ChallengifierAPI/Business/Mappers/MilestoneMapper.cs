using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
    }
}
