using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System.Linq;

namespace DataAccess.Repository
{
    public class UserRepository : BaseRepository<User, string>, IUserRepository
    {
        public UserRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }

        public User GetUserByLoggedInId(string id)
        {
            return DbContext.User.FirstOrDefault(u => u.User_AspNetUserID == id);
        }
    }
}
