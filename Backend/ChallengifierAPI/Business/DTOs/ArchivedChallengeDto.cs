using System;

namespace Business.DTOs
{
    public class ArchivedChallengeDto
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int AcceptedBy { get; set; }
    }
}
