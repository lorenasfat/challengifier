using Business.DTOs;

namespace Business.Services.Interfaces
{
    public interface IMailService
    {
        void NotifySubscribers();
        void AddDvdToQueue(MailDto mailDto);
    }
}
