using Business.DTOs;

namespace ChallengifierAPI.Infrastructure.Session
{
    public class SessionState
    {
        public static UserDto LoggedInUser { get; set; }
    }
}