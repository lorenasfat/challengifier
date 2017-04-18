using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.DTOs
{
    public class UserRankDto
    {
        public Guid Id { get; set; }
        public string UserId { get; set; }
        public double SystemGrade { get; set; }
        public double UsersGrade { get; set; }
        public double ChallengerGrade { get; set; }
        public double FinalGrade { get; set; }
    }
}
