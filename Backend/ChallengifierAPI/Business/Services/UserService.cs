using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;

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

        public bool Login(UserDto user)
        {
            var dbUser = _unitOfWork.UserRepository.GetById(user.UserId);
            if(ValidateUser(dbUser, user))
            {
                return true;
            }
            return false;
        }

        private bool ValidateUser(User dbUser, UserDto user)
        {
            if (dbUser.Username == user.Username)
                return true;
            return false;
        }

        public void Register(UserDto user)
        {
            try
            {
                _unitOfWork.UserRepository.Create(user.ToDbEntity());
                _unitOfWork.UserRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }
    }
}
