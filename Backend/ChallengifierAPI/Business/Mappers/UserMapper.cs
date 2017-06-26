using Business.DTOs;
using DataAccess.Entities;

namespace Business.Mappers
{
    public static class UserMapper
    { 
        public static UserDto ToDto(this AspNetUsers user)
        {
            return new UserDto()
            {
                AspNetUserId = user.Id,
                Username = user.Email,
                Points = user.Points
            };
        }
    }
}
