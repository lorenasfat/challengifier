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
                Suggested_Time_Interval = challenge.SuggestedTimeInterval
            };
        }

        public static IEnumerable<ChallengeDto> ToDtos(this IEnumerable<Challenge> challenges)
        {
            return challenges.Select(c => new ChallengeDto()
            {
                Name = c.Name,
                Description = c.Description,
                Id = c.Challenge_ID,
                SuggestedTimeInterval = c.Suggested_Time_Interval
            });
        }

        public static ChallengeDto ToDto(this Challenge c)
        {
            return new ChallengeDto()
            {
                Name = c.Name,
                Description = c.Description,
                Id = c.Challenge_ID,
                SuggestedTimeInterval = c.Suggested_Time_Interval
            };
        }
    }
}
