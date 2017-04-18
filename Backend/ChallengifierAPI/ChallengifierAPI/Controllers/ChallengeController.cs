using Business.DTOs;
using Business.Services.Interfaces;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    public class ChallengeController : ApiController
    {
        private readonly IChallengeService _challengeService;
        public ChallengeController(IChallengeService challengeService)
        {
            _challengeService = challengeService;
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
        public HttpResponseMessage GetChallenge(Guid id)
        {
            try
            {
                var challenge = _challengeService.GetChallengeById(id);
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
                _challengeService.AddChallenge(challenge);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added an objective!");
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
                return Request.CreateResponse(HttpStatusCode.OK, "Successfully updated an objective!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("delete")]
        public HttpResponseMessage DeleteChallenge(Guid id)
        {
            try
            {
                _challengeService.DeleteChallenge(id);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully deleted an objective!");
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
