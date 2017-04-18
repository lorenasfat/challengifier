using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.Repository
{
    public class UserRankRepository : BaseRepository<UserRank, Guid>, IUserRankRepository
    {
        public UserRankRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
    }
}
