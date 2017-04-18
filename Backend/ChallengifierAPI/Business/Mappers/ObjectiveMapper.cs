using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Business.Mappers
{
    public static class ObjectiveMapper
    {
        public static Objective ToDbEntity(this ObjectiveDto objective)
        {
            return new Objective()
            {
                Objective_ID = objective.Id,
                Challenge_ID = objective.ChallengeId,
                Deadline = objective.Deadline,
                Description = objective.Description,
                Expected_Outcome = objective.ExpectedOutcome,
                Name = objective.Name,
                End_Date = objective.EndDate,
                Status_ID = (int)objective.Status,
                Start_Date = objective.StartDate,
                User_ID = objective.UserID
            };
        }

        public static ObjectiveDto ToDto(this Objective objective)
        {
            return new ObjectiveDto()
            {
                ChallengeId = objective.Challenge_ID,
                Deadline = objective.Deadline,
                Description = objective.Description,
                EndDate = objective.End_Date.Value,
                ExpectedOutcome = objective.Expected_Outcome,
                Id = objective.Objective_ID,
                Name = objective.Name,
                StartDate = objective.Start_Date.Value,
                Status = (Common.Enums.ObjectiveStatus)Enum.ToObject(typeof(Common.Enums.ObjectiveStatus), objective.Status_ID),
                UserID = objective.User_ID,
            };
        }

        public static IEnumerable<ObjectiveDto> ToDtos(this IEnumerable<Objective> objectives)
        {
            return objectives.Select(objective => new ObjectiveDto()
            {
                ChallengeId = objective.Challenge_ID,
                Deadline = objective.Deadline,
                Description = objective.Description,
                EndDate = objective.End_Date.Value,
                ExpectedOutcome = objective.Expected_Outcome,
                Id = objective.Objective_ID,
                Name = objective.Name,
                StartDate = objective.Start_Date.Value,
                Status = (Common.Enums.ObjectiveStatus)Enum.ToObject(typeof(Common.Enums.ObjectiveStatus), objective.Status_ID),
                UserID = objective.User_ID,
            });
        }
    }
}
