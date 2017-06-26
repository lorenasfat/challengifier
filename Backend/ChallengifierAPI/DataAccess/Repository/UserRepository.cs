using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System.Linq;

namespace DataAccess.Repository
{
    public class UserRepository : BaseRepository<AspNetUsers, string>, IUserRepository
    {
        public UserRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
        
        public AspNetUsers GetUserByUsername(string username)
        {
            return DbContext.AspNetUsers.FirstOrDefault(u => u.Email == username);
        }
    }
}
