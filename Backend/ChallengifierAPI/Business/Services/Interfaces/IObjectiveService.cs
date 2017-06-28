using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IObjectiveService : IBaseService
    {
        void AddObjective(ObjectiveDto objective);
        void UpdateObjective(ObjectiveDto objective);
        void DeleteObjective(Guid objectiveId);
        IEnumerable<ObjectiveDto> GetAllObjectives();
        ObjectiveDto GetObjectiveById(Guid Id);
        int CountForChallenge(Guid challengeId);
        //TODO
        //IEnumerable<Objective> GetAllObjectivesForUser(Guid userID);
    }
}
