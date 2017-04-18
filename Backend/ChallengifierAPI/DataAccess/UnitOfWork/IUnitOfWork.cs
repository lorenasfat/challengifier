using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.UnitOfWork
{
    public interface IUnitOfWork : IDisposable
    {
        //add repos
        IMilestoneRepository MilestoneRepository{ get; set; }
        IChallengeRepository ChallengeRepository { get; set; }
        IObjectiveRepository ObjectiveRepository { get; set; }
        IPictureRepository PictureRepository { get; set; }
        IPlanningStepRepository PlanningStepRepository { get; set; }
        IUserRepository UserRepository { get; set; }
        IUserRatingRepository UserRatingRepository { get; set; }
        IUserRankRepository UserRankRepository { get; set; }
        IObjectiveStatusRepository ObjectiveStatusRepository { get; set; }

        void Commit();
        void RollBack();
    }
}
