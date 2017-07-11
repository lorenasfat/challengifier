using Business.Components.Interface;
using Business.DTOs;
using Business.Mappers;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Linq;

namespace Business.Components
{
    public class UserRankComponent : IUserRankComponent
    {
        private readonly IUnitOfWork _unitOfWork;

        public UserRankComponent(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }

        public int PersistSystemGradeForObjective(Guid objectiveID)
        {
            var grade = ComputeSystemGradeForObjective(objectiveID);

            try
            {
                _unitOfWork.ObjectiveRepository.AddSystemGrading(grade, objectiveID);
                _unitOfWork.ObjectiveRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception ex)
            {
                _unitOfWork.RollBack();
                throw;
            }
            return grade;
        }

        private int ComputeSystemGradeForObjective(Guid objectiveID)
        {
            var objective = _unitOfWork.ObjectiveRepository.All().Where(o => o.Objective_ID == objectiveID).FirstOrDefault();

            var duration = objective.End_Date.Value.Subtract(objective.Start_Date.Value).Days;

            var delay = objective.Deadline.Subtract(objective.End_Date.Value).Days;

            if (delay <= 0 || (DateTime.Compare(objective.End_Date.Value, objective.Deadline) <= 0))
                return 10;
            else if (delay > 0 && delay <= duration * 0.1)
                return 9;
            else if (delay > duration * 0.1 && delay <= duration * 0.2)
                return 8;
            else if (delay > duration * 0.2 && delay <= duration * 0.3)
                return 7;
            else if (delay > duration * 0.3 && delay <= duration * 0.4)
                return 6;
            else if (delay > duration * 0.4 && delay <= duration * 0.5)
                return 5;

            return 1;
        }

        public void Dispose()
        {
            _unitOfWork.Dispose();
        }
    }
}
