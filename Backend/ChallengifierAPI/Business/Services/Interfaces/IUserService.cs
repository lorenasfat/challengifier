using System.Security.Cryptography.X509Certificates;
using Business.DTOs;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IUserService : IBaseService
    {
        UserDto GetUserByUsername(string username);
        UserDto GetUserById(string id);
        IEnumerable<LeaderboardUserDto> GetLeaderboard();
    }
}
