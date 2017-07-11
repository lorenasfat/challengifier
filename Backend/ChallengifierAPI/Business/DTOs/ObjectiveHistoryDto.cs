using System;

namespace Business.DTOs
{
    public class ObjectiveHistoryDto
    {
        public Guid Id { get; set; }
        public String Name { get; set; }
        public String Description { get; set; }
        public int Grade { get; set; }
        public int Status { get; set; }
    }
}
