using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.Repository
{
    public class PlanningStepRepository : BaseRepository<PlanningStep, Guid>, IPlanningStepRepository
    {
        public PlanningStepRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
    }
}
