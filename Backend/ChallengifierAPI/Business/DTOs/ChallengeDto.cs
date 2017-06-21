using System;
using System.Collections;
using System.Collections.Generic;

namespace Business.DTOs
{
    public class ChallengeDto
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string Suggested_Time_UnitsId { get; set; }
        public string User_Id { get; set; }
        public int? Suggested_Time_Number { get; set; }
        public IEnumerable<PlanningStepDto> PlanningSteps { get; set; }
    }
}
