﻿using Common.Enums;
using System;

namespace Business.DTOs
{
    public class ObjectiveDto
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Deadline { get; set; }
        public string ExpectedOutcome { get; set; }
        public Guid? ChallengeId { get; set; }
        public int Status { get; set; }
        public DateTime? StartDate { get; set; }
        public DateTime? EndDate { get; set; }
        public string UserId { get; set; }
        public int Progress { get; set; }
    }
}
