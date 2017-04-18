//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Net;
//using System.Net.Http;
//using System.Web.Http;
//using ChallengifierAPI.Identity;
//using ChallengifierAPI.Models;
//using Microsoft.AspNet.Identity;
//using Microsoft.AspNet.Identity.EntityFramework;

//namespace ChallengifierAPI.Controllers
//{
//    public class AccountController : BaseController
//    {
//        [HttpPost]
//        [ActionName("login")]
//        [AllowAnonymous]
//        public HttpResponseMessage Login(LoginModel login)
//        {
//            if (ModelState.IsValid)
//            {

//                AppUser user = UserManager.FindAsync(login.Email, login.Password).Result;

//                if (user != null)
//                {
//                    string basicAuthToken = string.Format("Basic {0}:{1}", login.Email, login.Password);
//                    var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(basicAuthToken);

//                    return Request.CreateResponse(HttpStatusCode.OK, Convert.ToBase64String(plainTextBytes));
//                }
//            }
//            return Request.CreateResponse(HttpStatusCode.Forbidden, "Not a valid user.");
//        }

//        [HttpPost]
//        [AllowAnonymous]
//        [ActionName("register")]
//        public HttpResponseMessage Register(RegisterModel model)
//        {
//            if (!ModelState.IsValid)
//            {
//                return Request.CreateResponse(HttpStatusCode.Forbidden, "Invalid data");
//            }
//            var user = new AppUser
//            {
//                Email = model.Email,
//                UserName = model.Email
//            };
//            var result = UserManager.CreateAsync(user, model.Password).Result;
//            if (result.Succeeded)
//            {
//                return Request.CreateResponse(HttpStatusCode.OK, "Account created");
//            }
//            return Request.CreateResponse(HttpStatusCode.Forbidden, "Failed to create account");
//        }

//        [HttpPost]
//        [ActionName("admin")]
//        [Authorize(Roles = "Asd")]

//        public HttpResponseMessage Admin(LoginModel login)
//        {
//            AppUser user = UserManager.Find(login.Email, login.Password);
//            if (user == null)
//            {
//                return Request.CreateResponse(HttpStatusCode.Forbidden, "Not a valid user.");
//            }

//            return Request.CreateResponse(HttpStatusCode.OK, "Access granted");
//        }

//        public string GetString()
//        {
//            return "gfgf";
//        }
//    }
//}
