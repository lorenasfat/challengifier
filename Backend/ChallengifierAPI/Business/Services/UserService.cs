using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Linq;

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

        public UserDto getUserById(string id)
        {
            return _unitOfWork.UserRepository.All().Where(u => u.Id == id).FirstOrDefault().ToDto();
                }

        public UserDto getUserByUsername(string username)
        {
            return _unitOfWork.UserRepository.GetUserByUsername(username).ToDto();
        }
    }
}
