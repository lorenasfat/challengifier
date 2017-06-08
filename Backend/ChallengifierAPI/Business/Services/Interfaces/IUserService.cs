using System.Security.Cryptography.X509Certificates;
using Business.DTOs;

namespace Business.Services.Interfaces
{
    public interface IUserService : IBaseService
    {
        UserDto getUserByUsername(string username);
    }
}
