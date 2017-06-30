using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;
using System.Data.Entity;

namespace DataAccess.UnitOfWork
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly ChallengifierEntities1 dbContext;
        private DbContextTransaction transaction;

        public IMilestoneRepository MilestoneRepository { get; set; }
        public IChallengeRepository ChallengeRepository { get; set; }
        public IObjectiveRepository ObjectiveRepository { get; set; }
        public IPictureRepository PictureRepository { get; set; }
        public IPlanningStepRepository PlanningStepRepository { get; set; }
        public IUserRepository UserRepository { get; set; }
        public IUserRatingRepository UserRatingRepository { get; set; }
        public IUserRankRepository UserRankRepository { get; set; }
        public IObjectiveStatusRepository ObjectiveStatusRepository { get; set; }

        public UnitOfWork(ChallengifierEntities1 dbContext, 
            IMilestoneRepository milestoneRepository, 
            IChallengeRepository challengeRepository,
            IObjectiveRepository objectiveRepository,
            IPictureRepository pictureRepository,
            IPlanningStepRepository planningStepRepository,
            IUserRankRepository userRankRepository,
            IUserRatingRepository userRatingRepository,
            IUserRepository userRepository)
        {
            this.dbContext = dbContext;
            this.MilestoneRepository = milestoneRepository;
            this.ChallengeRepository = challengeRepository;
            this.ObjectiveRepository = objectiveRepository;
            this.PictureRepository = pictureRepository;
            this.PlanningStepRepository = planningStepRepository;
            this.UserRankRepository = userRankRepository;
            this.UserRatingRepository = userRatingRepository;
            this.UserRepository = userRepository;
            transaction = dbContext.Database.BeginTransaction();
        }
        public void Commit()
        {
            dbContext.SaveChanges();
            transaction.Commit();
            transaction = dbContext.Database.BeginTransaction();
        }

        public void RollBack()
        {
            transaction.Rollback();
        }

        private bool disposed = false;

        protected virtual void Dispose(bool disposing)
        {
            if (!this.disposed)
            {
                if (disposing)
                {
                    transaction.Dispose();
                    this.dbContext.Dispose();
                }
            }
            this.disposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}
