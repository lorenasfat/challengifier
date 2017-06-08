using DataAccess.Entities;
using System;

namespace DataAccess.Repository.Interfaces
{
    public interface IUserRepository : IBaseRepository<User, string>
    {
        User GetUserByLoggedInId(string id);
        AspNetUsers GetUserByUsername(string username);
    }
}
