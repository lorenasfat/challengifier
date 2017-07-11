using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Repository.Interfaces
{
    public interface IObjectiveRepository : IBaseRepository<Objective, Guid>
    {
        void AddSystemGrading(int grade, Guid objectiveID);
    }
}
