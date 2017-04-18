using System;
using System.Collections.Generic;
using System.Linq;
using System.Messaging;
using System.Text;
using System.Threading.Tasks;
using Business.DTOs;

namespace Business.Components
{
    public class MSMQService
    {
        static public void InsertMessageInQueue(MailDto mailDto)
        {
            MessageQueue msmq;
            try
            {
                string qname = @".\Private$\Mails";
                if (!MessageQueue.Exists(qname))
                {
                    //Console.Write("No such Q path! Creating...");
                    msmq = MessageQueue.Create(qname);
                    msmq.Label = "test";
                    msmq.Send(mailDto);
                }
                else
                {
                    msmq = new MessageQueue(qname);
                    msmq.Label = "test";
                    msmq.Send(mailDto);
                }
                msmq.Close();
            }
            catch
            {
                throw;
            }
        }
        static public MailDto RetriveMessageFromQueue()
        {
            try
            {
                string qname = @".\Private$\Dvds";
                MessageQueue msmq = new MessageQueue(qname);
                msmq.Formatter = new XmlMessageFormatter(new Type[] { typeof(MailDto) });
                var dvd = (MailDto)msmq.Receive().Body;

                //var dvds = msmq.GetAllMessages();
                //var dvd = dvds[dvds.Length - 1];
                return dvd;
            }
            catch
            {
                throw;
            }
        }
    }
}
