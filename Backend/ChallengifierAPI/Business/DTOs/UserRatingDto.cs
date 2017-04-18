using System;

namespace Business.DTOs
{
    public class UserRatingDto
    {
        public Guid Id { get; set; }
        public string UserID { get; set; }
        public Guid ObjectiveId { get; set; }
        public int Grade { get; set; }
    }
}
