using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IChallengeService : IBaseService
    {
        IEnumerable<ChallengeDto> GetAllChallenges();
        ChallengeDto GetChallengeById(Guid challengeId);
        void AddChallenge(ChallengeDto challenge);
        void UpdateChallenge(ChallengeDto challenge);
        void DeleteChallenge(Guid challengeId);
        ChallengeDto GetChallengeByName(string name);
        IEnumerable<MyChallengeDto> GetChallengesOfUser(string id);
        int CountObjectivesForReview(Guid id);
    }
}
