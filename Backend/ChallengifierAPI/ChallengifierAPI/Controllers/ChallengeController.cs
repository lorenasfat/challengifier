using Business.DTOs;
using Business.Services.Interfaces;
using ChallengifierAPI.Infrastructure.Session;
using System;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    public class ChallengeController : BaseController
    {
        private readonly IChallengeService _challengeService;
        private readonly IUserService _userService;

        public ChallengeController(IChallengeService challengeService, IUserService userService)
        {
            _challengeService = challengeService;
            _userService = userService;
        }

        [HttpGet]
        [ActionName("all")]
        public HttpResponseMessage GetChallenges()
        {
            try
            {
                var challenges = _challengeService.GetAllChallenges();
                if (challenges == null)
                    return Request.CreateResponse(HttpStatusCode.OK, "No entries!");

                return Request.CreateResponse(HttpStatusCode.OK, challenges);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        [ActionName("get")]
        public HttpResponseMessage GetUserChallenges(string id)
        {
            try
            {
                var challenges = _challengeService.GetChallengesOfUser(id);
                if (challenges == null)
                {
                    return Request.CreateResponse(HttpStatusCode.NotFound);
                }
                return Request.CreateResponse(HttpStatusCode.OK, challenges);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpGet]
        [ActionName("name")]
        public HttpResponseMessage GetChallenge(string name)
        {
            try
            {
                var challenge = _challengeService.GetChallengeByName(name);
                if (challenge == null)
                {
                    return Request.CreateResponse(HttpStatusCode.NotFound);
                }
                return Request.CreateResponse(HttpStatusCode.OK, challenge);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
        [HttpPost]
        [ActionName("add")]
        public HttpResponseMessage AddChallenge(ChallengeDto challenge)
        {
            try
            {
                challenge.User_Id = SessionState.LoggedInUser == null ? _userService.getUserByUsername(challenge.User_Id).AspNetUserId : SessionState.LoggedInUser.AspNetUserId;

                _challengeService.AddChallenge(challenge);
                return Request.CreateResponse(HttpStatusCode.Created, challenge);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("update")]
        public HttpResponseMessage UpdateChallenge(ChallengeDto challenge)
        {
            try
            {
                _challengeService.UpdateChallenge(challenge);
                return Request.CreateResponse(HttpStatusCode.OK, challenge);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpDelete]
        [ActionName("delete")]
        public HttpResponseMessage DeleteChallenge(Guid id)
        {
            try
            {
                _challengeService.DeleteChallenge(id);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully deleted a challenge!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                _challengeService.Dispose();
            }

            base.Dispose(disposing);
        }
    }
}
