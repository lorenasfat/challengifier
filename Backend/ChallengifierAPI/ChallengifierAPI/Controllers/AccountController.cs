using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web.Http;
using Business.Services.Interfaces;
using ChallengifierAPI.Identity;
using ChallengifierAPI.Infrastructure.Session;
using ChallengifierAPI.Models;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;

namespace ChallengifierAPI.Controllers
{
    public class AccountController : BaseController
    {
        private readonly IUserService _userService;

        public AccountController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpPost]
        [ActionName("login")]
        [AllowAnonymous]
        public HttpResponseMessage Login(LoginModel login)
        {
            if (ModelState.IsValid)
            {

                AppUser user = UserManager.FindAsync(login.Email, login.Password).Result;

                if (user != null)
                {
                    string basicAuthToken = string.Format("Basic {0}:{1}", login.Email, login.Password);
                    var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(basicAuthToken);

                    SessionState.LoggedInUser = _userService.getUserByUsername(login.Email);

                    var resp = new HttpResponseMessage(HttpStatusCode.OK);
                    resp.Content = new StringContent(Convert.ToBase64String(plainTextBytes), System.Text.Encoding.UTF8, "text/plain");
                    return resp;
                }
            }
            var negativeResp = new HttpResponseMessage(HttpStatusCode.Forbidden);
            negativeResp.Content = new StringContent("Invalid credentials.", System.Text.Encoding.UTF8, "text/plain");
            return negativeResp;
        }

        [HttpPost]
        [AllowAnonymous]
        [ActionName("register")]
        public HttpResponseMessage Register(RegisterModel model)
        {
            if (!ModelState.IsValid)
            {
                return Request.CreateResponse(HttpStatusCode.Forbidden, "Invalid data");
            }
            var user = new AppUser
            {
                Email = model.Email,
                UserName = model.Email
            };
            var result = UserManager.CreateAsync(user, model.Password).Result;
            if (result.Succeeded)
            {
                return Request.CreateResponse(HttpStatusCode.OK, "Account created");
            }
            return Request.CreateResponse(HttpStatusCode.Forbidden, "Failed to create account");
        }

        [HttpPost]
        [ActionName("admin")]
        [Authorize(Roles = "Asd")]

        public HttpResponseMessage Admin(LoginModel login)
        {
            AppUser user = UserManager.Find(login.Email, login.Password);
            if (user == null)
            {
                return Request.CreateResponse(HttpStatusCode.Forbidden, "Not a valid user.");
            }

            return Request.CreateResponse(HttpStatusCode.OK, "Access granted");
        }

        public string GetString()
        {
            return "gfgf";
        }
    }
}
