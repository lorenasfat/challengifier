using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.DTOs
{
    public class PictureDto
    {
        public Guid Id { get; set; }
        public byte[] Picture { get; set; }
        public Guid? ChallengeId { get; set; }
        public Guid? ObjectiveId { get; set; }
        public Guid? MilestoneId { get; set; }
    }
}
