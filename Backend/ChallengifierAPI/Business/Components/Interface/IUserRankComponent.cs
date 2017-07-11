using Business.Services.Interfaces;
using System;

namespace Business.Components.Interface
{
    public interface IUserRankComponent
    {
        int PersistSystemGradeForObjective(Guid objectiveID);
    }
}
