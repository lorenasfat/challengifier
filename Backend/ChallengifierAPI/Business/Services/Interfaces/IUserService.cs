using Business.DTOs;

namespace Business.Services.Interfaces
{
    public interface IUserService : IBaseService
    {
        bool Login(UserDto user);
        void Register(UserDto user);
    }
}
