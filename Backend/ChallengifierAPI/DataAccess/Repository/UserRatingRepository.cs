using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.Repository
{
    public class UserRatingRepository : BaseRepository<UserRating, Guid>, IUserRatingRepository
    {
        public UserRatingRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
    }
}
