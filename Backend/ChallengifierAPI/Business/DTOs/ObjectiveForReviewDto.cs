using System;

namespace Business.DTOs
{
    public class ObjectiveForReviewDto
    {
        public Guid Id { get; set; }
        public string ObjectiveName { get; set; }
        public DateTime From { get; set; }
        public DateTime To { get; set; }
        public DateTime Deadline { get; set; }
        public string Username { get; set; }
        public string Description { get; set; }
        public string ExpectedOutcome { get; set; }
    }
}
