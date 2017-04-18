using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.DTOs
{
    public class MailDto
    {
        public string Subject { get; set; }
        public string Content { get; set; }
        public string To { get; set; }
        public string From { get; set; }
    }
}
