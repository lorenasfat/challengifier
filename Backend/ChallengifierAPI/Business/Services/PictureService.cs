using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Business.Services
{
    public class PictureService : IPictureService
    {
        private readonly IUnitOfWork _unitOfWork;

        public PictureService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public void AddPicture(PictureDto picture)
        {
            try
            {
                _unitOfWork.PictureRepository.Create(picture.ToDbEntity());
                _unitOfWork.PictureRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void DeletePicture(Guid pictureId)
        {
            try
            {
                var dbEntity = _unitOfWork.PictureRepository.GetById(pictureId);
                _unitOfWork.PictureRepository.Delete(dbEntity);
                _unitOfWork.PictureRepository.Save();
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

        public IEnumerable<Picture> GetPicturesForChallenge(Guid challengeId)
        {
            return _unitOfWork.PictureRepository.All().Where(p=> p.Challenge_ID == challengeId);
        }

        public IEnumerable<Picture> GetPicturesForMilestone(Guid milestoneId)
        {
            return _unitOfWork.PictureRepository.All().Where(p => p.Milestone_ID == milestoneId);
        }

        public IEnumerable<Picture> GetPicturesForObjective(Guid objectiveId)
        {
            return _unitOfWork.PictureRepository.All().Where(p => p.Objective_ID == objectiveId);
        }
    }
}
