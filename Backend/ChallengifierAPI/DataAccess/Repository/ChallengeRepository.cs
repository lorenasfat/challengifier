using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.Repository
{
    public class ChallengeRepository : BaseRepository<Challenge, Guid>, IChallengeRepository
    {
        public ChallengeRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
    }
}
