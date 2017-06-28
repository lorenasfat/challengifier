using System;

namespace Business.DTOs
{
    public class MyChallengeDto
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string Suggested_Time_UnitsId { get; set; }
        public string User_Id { get; set; }
        public int? Suggested_Time_Number { get; set; }
        public int Acceptance { get; set; }
    }
}
