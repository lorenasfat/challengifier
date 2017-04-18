using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IMilestoneService : IBaseService
    {
        void AddMilestone(MilestoneDto milestone);
        void DeleteMilestone(Guid id);
        void UpdateMilestone(MilestoneDto milestone);
        IEnumerable<Milestone> GetAllMilestones();
        Milestone GetMilestoneById(Guid id);
        //TODO
        //IEnumerable<Milestone> GetAllMilestonesForUser(Guid userId);
    }
}
