using Business.DTOs;
using System;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IObjectiveService : IBaseService
    {
        void AddObjective(ObjectiveDto objective);
        void UpdateObjective(ObjectiveDto objective);
        void DeleteObjective(Guid objectiveId);
        IEnumerable<ObjectiveDto> GetAllObjectives(string id);
        IEnumerable<ObjectiveHistoryDto> GetHistoryObjectives(string id);
        ObjectiveDto GetObjectiveById(Guid Id);
        int CountForChallenge(Guid challengeId);
        IEnumerable<ObjectiveForReviewDto> GetObjectivesForReview(Guid id);
        void AddObjectiveRating(UserRatingDto rating);
        void UpdateChallengeBasedGrade(ObjectiveDto objective);
        void UpdateGrade(ObjectiveDto objective);
    }
}
