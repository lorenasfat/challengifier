using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repository
{
    public class ObjectiveRepository : BaseRepository<Objective, Guid>, IObjectiveRepository
    {
        public ObjectiveRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }

        public void AddSystemGrading(int grade, Guid objectiveID)
        {
            DbContext.SystemRating.Add(new SystemRating()
            {
                Id = Guid.NewGuid(),
                ObjectiveId = objectiveID,
                System_Grade = grade
            });
        }
    }
}
