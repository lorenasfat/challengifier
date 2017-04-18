using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.Mappers
{
    public static class PictureMapper
    {
        public static Picture ToDbEntity(this PictureDto picture)
        {
            return new Picture()
            {
                Challenge_ID = picture.ChallengeId,
                Milestone_ID = picture.MilestoneId,
                Objective_ID = picture.ObjectiveId,
                Picture_ID = picture.Id,
                Picture1 = picture.Picture
            };
        }
    }
}
