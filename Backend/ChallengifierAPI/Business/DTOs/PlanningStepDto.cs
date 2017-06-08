using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.DTOs
{
    public class PlanningStepDto
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public Guid ChallengeId { get; set; }
        public string TimeUnitId { get; set; }
        public int? TimeUnitNumber { get; set; }
    }
}
