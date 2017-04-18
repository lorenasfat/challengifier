using Business.DTOs;
using DataAccess.Entities;

namespace Business.Mappers
{
    public static class UserRatingMapper
    {
        public static UserRating ToDbEntity(this UserRatingDto rating)
        {
            return new UserRating()
            {
                Objective_ID = rating.ObjectiveId,
                Rating_ID = rating.Id,
                User_ID = rating.UserID,
                Grade = rating.Grade
            };
        }
    }
}
