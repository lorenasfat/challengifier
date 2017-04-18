using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;

namespace Business.Services.Interfaces
{
    public interface IPictureService : IBaseService
    {
        void AddPicture(PictureDto picture);
        void DeletePicture(Guid pictureId);
        IEnumerable<Picture> GetPicturesForChallenge(Guid challengeId);
        IEnumerable<Picture> GetPicturesForObjective(Guid objectiveId);
        IEnumerable<Picture> GetPicturesForMilestone(Guid milestoneId);
    }
}
