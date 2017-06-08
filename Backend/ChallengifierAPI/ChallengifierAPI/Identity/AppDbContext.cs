using Microsoft.AspNet.Identity.EntityFramework;

namespace ChallengifierAPI.Identity
{
    public class AppDbContext : IdentityDbContext<AppUser>
    {
        public AppDbContext()
            : base("MyIdentityConnectionString")
        {
        }
    }
}