using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.UnitOfWork;
using System;

namespace Business.Services
{
    public class UserRatingService : IUserRatingService
    {
        private readonly IUnitOfWork _unitOfWork;

        public UserRatingService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }
        public void AddRating(UserRatingDto rating)
        {
            try
            {
                _unitOfWork.UserRatingRepository.Create(rating.ToDbEntity());
                _unitOfWork.UserRatingRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void Dispose()
        {
            _unitOfWork.Dispose();
        }
    }
}
