using Business.DTOs;

namespace Business.Services.Interfaces
{
    public interface IUserRatingService : IBaseService
    {
        void AddRating(UserRatingDto rating);
    }
}
