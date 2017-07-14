using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Linq;
using System.Collections.Generic;

namespace Business.Services
{

    public class UserService : IUserService
    {
        private readonly IUnitOfWork _unitOfWork;

        public UserService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }
        public void Dispose()
        {
            _unitOfWork.Dispose();
        }

        public IEnumerable<LeaderboardUserDto> GetLeaderboard()
        {
            var users = _unitOfWork.UserRepository.All().OrderByDescending(u=>u.Points);

            return users.Select(u => new LeaderboardUserDto()
            {
                Username = u.UserName,
                Points = u.Points
            });
        }

        public UserDto GetUserById(string id)
        {
            return _unitOfWork.UserRepository.All().Where(u => u.Id == id).FirstOrDefault().ToDto();
                }

        public UserDto GetUserByUsername(string username)
        {
            return _unitOfWork.UserRepository.GetUserByUsername(username).ToDto();
        }
    }
}
