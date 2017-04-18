using System;

namespace Business.DTOs
{
    public class ChallengeDto
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string SuggestedTimeInterval { get; set; }
    }
}
