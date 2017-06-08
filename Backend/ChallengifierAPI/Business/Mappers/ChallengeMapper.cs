using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Business.DTOs;
using DataAccess.Entities;

namespace Business.Mappers
{
    public static class ChallengeMapper
    {
        public static Challenge ToDbEntity(this ChallengeDto challenge)
        {
            return new Challenge()
            {
                Challenge_ID = challenge.Id,
                Description = challenge.Description,
                Name = challenge.Name,
                Suggested_Time_Number = challenge.Suggested_Time_Number,
                Suggested_Time_UnitsId = challenge.Suggested_Time_UnitsId
            };
        }

        public static IEnumerable<ChallengeDto> ToDtos(this IEnumerable<Challenge> challenges)
        {
            var dtos = challenges.Select(c => new ChallengeDto()
            {
                Name = c.Name,
                Description = c.Description,
                Id = c.Challenge_ID,
                Suggested_Time_UnitsId = c.Suggested_Time_UnitsId,
                Suggested_Time_Number = c.Suggested_Time_Number,
                PlanningSteps = c.PlanningStep.Select(p => new PlanningStepDto()
                {
                    ChallengeId = p.Challenge_ID,
                    Description = p.Description,
                    Id = p.PlanningStep_ID,
                    Name = p.Name,
                    TimeUnitId = p.Duration_TimeUnitId,
                    TimeUnitNumber = p.Duration_TimeNumber
                })
            });
            return dtos;
        }

        public static ChallengeDto ToDto(this Challenge c)
        {
            return new ChallengeDto()
            {
                Name = c.Name,
                Description = c.Description,
                Id = c.Challenge_ID,
                Suggested_Time_UnitsId = c.Suggested_Time_UnitsId,
                Suggested_Time_Number = c.Suggested_Time_Number
            };
        }
    }
}
