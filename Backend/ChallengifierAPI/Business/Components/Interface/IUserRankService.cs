using Business.DTOs;
using Business.Services.Interfaces;
using System;

namespace Business.Components.Interface
{
    public interface IUserRankComponent : IBaseService
    {
        UserRankDto ComputeUserRank(string userID);
    }
}
