using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Business.Components;
using Business.DTOs;

namespace Business.Services
{
    public class MailService
    {
        public void NotifySubscribers()
        {
            var mailBody = MSMQService.RetriveMessageFromQueue();
            MailSender.SendMail(mailBody);
        }

        public void AddDvdToQueue(MailDto mailDto)
        {
            MSMQService.InsertMessageInQueue(mailDto);
        }
    }
}
