using Business.DTOs;
using Business.Services.Interfaces;

namespace Business.Components.Interface
{
    public interface IUserRankComponent : IBaseService
    {
        UserRankDto ComputeUserRank(string userID);
        void GetObjectiveRank(string userID, string objectiveID);
    }
}
