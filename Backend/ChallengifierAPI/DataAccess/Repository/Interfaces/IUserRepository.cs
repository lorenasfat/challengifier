using DataAccess.Entities;
using System;

namespace DataAccess.Repository.Interfaces
{
    public interface IUserRepository : IBaseRepository<AspNetUsers, string>
    {
        AspNetUsers GetUserByUsername(string username);
    }
}
