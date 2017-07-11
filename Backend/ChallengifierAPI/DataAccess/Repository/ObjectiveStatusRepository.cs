using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.Repository
{
    public class ObjectiveStatusRepository : BaseRepository<ObjectiveStatus, Guid>, IObjectiveStatusRepository
    {
        public ObjectiveStatusRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }        
    }
}
