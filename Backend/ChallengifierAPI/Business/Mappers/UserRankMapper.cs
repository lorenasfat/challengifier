using Business.DTOs;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.Mappers
{
    public static class UserRankMapper
    {
        public static UserRankDto ToDto(this UserRank rank)
        {
            return new UserRankDto()
            {
                Id = rank.Rank_ID,
                FinalGrade = rank.Final_Grade,
                SystemGrade = rank.System_Grade,
                UserId = rank.User_ID,
                UsersGrade = rank.Users_Grade.Value
            };
        }

        public static UserRank ToDbEntity(this UserRankDto rank)
        {
            return new UserRank()
            {
                User_ID = rank.UserId,
                Final_Grade = rank.FinalGrade,
                Rank_ID = rank.Id,
                System_Grade = rank.SystemGrade,
                Users_Grade = rank.UsersGrade
            };
        }
    }
}
