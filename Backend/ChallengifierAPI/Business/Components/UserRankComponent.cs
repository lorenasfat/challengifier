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
        public UserRankDto ComputeUserRank(string userID)
        {
            var objectiveIds = _unitOfWork.ObjectiveRepository.All().Where(o=>o.User_ID == userID).Select(o => o.Objective_ID).ToList();
            foreach (var objectiveId in objectiveIds)
            {
                SetUpObjectiveRating(objectiveId);
            }
            return ComputeFinalGrade(userID);
        }

        public void SetUpObjectiveRating(Guid objectiveID)
        {
            var objective = _unitOfWork.ObjectiveRepository.GetById(objectiveID);

            objective.Rating = GetFinalGradeForObjective(objectiveID);

            _unitOfWork.ObjectiveRepository.Save();
            _unitOfWork.Commit();
        }

        public double GetFinalGradeForObjective(Guid objectiveID)
        {
            double? usersGrade = ComputeUsersGradeForObjective(objectiveID);
            double systemGrade = ComputeSystemGradeForObjective(objectiveID);
            double? challengerGrade = GetChallengerGradeForObjective(objectiveID);
            return challengerGrade == null ? ComputeFinalGrade(systemGrade, usersGrade.Value) : ComputeFinalGrade(systemGrade, usersGrade.Value, challengerGrade.Value);
        }

        public UserRankDto ComputeFinalGrade(string userID)
        {
            var dbRank = _unitOfWork.UserRankRepository.All().Where(r => r.User_ID == userID).SingleOrDefault();

            double? usersGrade = ComputeUsersGrade(userID);
            double systemGrade = ComputeSystemGrade(userID);
            double? challengerGrade = GetChallengerGrade(userID);
            double finalGrade = ComputeRank(userID);

            if (dbRank != null)
            {
                dbRank.Final_Grade = finalGrade;
                dbRank.System_Grade = systemGrade;
                dbRank.Users_Grade = usersGrade;

                _unitOfWork.UserRankRepository.Save();
                _unitOfWork.Commit();

                return dbRank.ToDto();
            }
            else
            {
                var rank = new UserRank()
                {
                    Final_Grade = finalGrade,
                    Rank_ID = Guid.NewGuid(),
                    System_Grade = systemGrade,
                    User_ID = userID,
                    Users_Grade = usersGrade
                };
                _unitOfWork.UserRankRepository.Create(rank);
                _unitOfWork.UserRankRepository.Save();
                _unitOfWork.Commit();

                return rank.ToDto();
            }
        }

        private double ComputeRank(string userID)
        {
            var objectiveIds = _unitOfWork.ObjectiveRepository.All().Where(o => o.User_ID == userID).Select(o => o.Objective_ID).ToList();
            int numberOfReviews = objectiveIds.Count;
            double sumOfGrades = 0;
            foreach (var objectiveID in objectiveIds)
            {
                sumOfGrades += GetFinalGradeForObjective(objectiveID);
            }
            return sumOfGrades / numberOfReviews;
        }

        private double? GetChallengerGrade(string userID)
        {
            var objectiveIds = _unitOfWork.ObjectiveRepository.All().Where(o => o.User_ID == userID).Select(o => o.Objective_ID).ToList();
            int numberOfReviews = objectiveIds.Count;
            double sumOfGrades = 0;
            foreach (var objectiveID in objectiveIds)
            {
                sumOfGrades += GetChallengerGradeForObjective(objectiveID);
            }
            return sumOfGrades / numberOfReviews;
        }

        private double ComputeSystemGrade(string userID)
        {
            //TODO
            return 10;
        }

        private double? ComputeUsersGrade(string userID)
        {
            var objectiveIds = _unitOfWork.ObjectiveRepository.All().Where(o => o.User_ID == userID).Select(o => o.Objective_ID).ToList();
            int numberOfReviews = objectiveIds.Count;
            double sumOfGrades = 0;
            foreach (var objectiveID in objectiveIds)
            {
                sumOfGrades += ComputeUsersGradeForObjective(objectiveID);
            }
            return sumOfGrades / numberOfReviews;
        }

        private double ComputeFinalGrade(double systemGrade, double usersGrade, double challengerGrade)
        {
            return 0.2 * systemGrade + 0.6 * usersGrade + 0.2 * challengerGrade;
        }

        private double ComputeFinalGrade(double systemGrade, double usersGrade)
        {
            return 0.3 * systemGrade + 0.7 * usersGrade;
        }

        private double GetChallengerGradeForObjective(Guid objectiveID)
        {
            var objective = _unitOfWork.ObjectiveRepository.GetById(objectiveID);
            var challenger = _unitOfWork.ChallengeRepository.GetById(objective.Challenge_ID.Value).User_ID;
            return _unitOfWork.UserRatingRepository.All().Where(r => r.Objective_ID == objectiveID && r.User_ID == challenger).SingleOrDefault().Grade;
        }

        private double ComputeSystemGradeForObjective(Guid objectiveID)
        {
            //TODO
            return 10;
        }

        private double ComputeUsersGradeForObjective(Guid objectiveID)
        {
            int numberOfReviews = 0;
            double sumOfGrades = 0;

            var reviewGrades = _unitOfWork.UserRatingRepository.All().Where(r => r.Objective_ID == objectiveID).Select(r => r.Grade).ToList();
            numberOfReviews = reviewGrades.Count;
            foreach (var grade in reviewGrades)
            {
                sumOfGrades += grade;
            }

            return sumOfGrades / numberOfReviews;
        }

        public void Dispose()
        {
            _unitOfWork.Dispose();
        }
    }
}
