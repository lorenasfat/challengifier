using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using ChallengifierAPI.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;

namespace ChallengifierAPI.Controllers
{
    public class BaseController : ApiController
    {
        //protected AppUserManager UserManager
        //{
        //    get
        //    {
        //        return System.Web.HttpContext.Current.GetOwinContext().GetUserManager<AppUserManager>();
        //    }
        //}

        //protected IAuthenticationManager AuthenticationManager
        //{
        //    get
        //    {
        //        return System.Web.HttpContext.Current.GetOwinContext().Authentication;
        //    }
        //}

        //protected AppRoleManager RoleManager
        //{
        //    get
        //    {
        //        return System.Web.HttpContext.Current.GetOwinContext().Get<AppRoleManager>();
        //    }
        //}
    }
}
