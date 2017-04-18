using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repository
{
    public class MilestoneRepository : BaseRepository<Milestone, Guid>, IMilestoneRepository
    {
        public MilestoneRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
    }
}
