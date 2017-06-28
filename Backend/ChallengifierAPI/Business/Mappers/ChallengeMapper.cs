using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Business.DTOs;
using DataAccess.Entities;
using System;
using Business.Services.Interfaces;
using Business.Services;
using DataAccess.UnitOfWork;

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
                Suggested_Time_UnitsId = challenge.Suggested_Time_UnitsId,
                User_ID = challenge.User_Id
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
                User_Id = c.User_ID,
            });
            return dtos;
        }

        public static IEnumerable<MyChallengeDto> ToMyDtos(this IEnumerable<Challenge> challenges)
        {

                var dtos = challenges.Select(c => new MyChallengeDto()
                {
                    Name = c.Name,
                    Description = c.Description,
                    Id = c.Challenge_ID,
                    Suggested_Time_UnitsId = c.Suggested_Time_UnitsId,
                    Suggested_Time_Number = c.Suggested_Time_Number,
                    User_Id = c.User_ID
                });
            
            return dtos.ToList();
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
