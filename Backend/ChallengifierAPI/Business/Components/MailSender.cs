using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;
using Business.DTOs;

namespace Business.Components
{
    public static class MailSender
    {
        public static bool SendMail(MailDto mailDto)
        {
            try
            {
                MailMessage mail = new MailMessage();
                SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");

                mail.From = new MailAddress(mailDto.From);
                mail.To.Add(mailDto.To);
                mail.Subject = mailDto.Subject;
                mail.Body = mailDto.Content;

                SmtpServer.Port = 587;
                SmtpServer.Credentials = new System.Net.NetworkCredential("lorena.sfat@gmail.com", "Lavanda112");
                SmtpServer.EnableSsl = true;

                SmtpServer.Send(mail);
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }
    }
}
