using Business.DTOs;
using Business.Services.Interfaces;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ChallengifierAPI.Infrastructure.Session;
using System.Collections.Generic;

namespace ChallengifierAPI.Controllers
{
    public class ObjectiveController : BaseController
    {
        private readonly IObjectiveService _objectiveService;
        private readonly IUserService _userService;

        public ObjectiveController(IObjectiveService objectiveService, IUserService userService)
        {
            _objectiveService = objectiveService;
            _userService = userService;
        }

        [HttpGet]
        [ActionName("get")]
        public HttpResponseMessage GetObjective(Guid id)
        {
            try
            {
                var objective = _objectiveService.GetObjectiveById(id);
                if (objective == null)
                    return Request.CreateResponse(HttpStatusCode.NotFound);

                return Request.CreateResponse(HttpStatusCode.OK, objective);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpGet]
        [ActionName("review")]
        public HttpResponseMessage GetObjectivesForReview(Guid id)
        {
            try
            {
                var objectives = _objectiveService.GetObjectivesForReview(id);
                if (objectives == null)
                    return Request.CreateResponse(HttpStatusCode.NotFound);

                return Request.CreateResponse(HttpStatusCode.OK, objectives);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("add")]
        public HttpResponseMessage AddObjective([FromBody]ObjectiveDto objective)
        {
            try
            {
                _objectiveService.AddObjective(objective);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added an objective!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("rate")]
        public HttpResponseMessage AddObjectiveRating([FromBody]UserRatingDto rating)
        {
            try
            {
                _objectiveService.AddObjectiveRating(rating);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added the rating!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        [ActionName("all")]
        public HttpResponseMessage GetObjectives(string id)
        {
            try
            {
                var objectives = _objectiveService.GetAllObjectives(id).ToList();
                if (objectives.Count == 0)
                    return Request.CreateResponse(HttpStatusCode.OK, "No entries yet!");

                return Request.CreateResponse(HttpStatusCode.OK, objectives);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        [ActionName("history")]
        public HttpResponseMessage GetHistoryObjectives([FromUri]string id)
        {
            try
            {
                var objectives = _objectiveService.GetHistoryObjectives(id).ToList();
                if (objectives.Count == 0)
                    return Request.CreateResponse(HttpStatusCode.OK, new List<ObjectiveHistoryDto>(){ new ObjectiveHistoryDto()
                    {
                        Name = "No past objectives :(",
                        Description = string.Empty,
                    } });

                return Request.CreateResponse(HttpStatusCode.OK, objectives);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("update")]
        public HttpResponseMessage UpdateObjective([FromBody]ObjectiveDto objective)
        {
            try
            {
                _objectiveService.UpdateObjective(objective);
                var userInfo = _userService.GetUserById(objective.UserId);
                return Request.CreateResponse(HttpStatusCode.OK, userInfo);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpDelete]
        [ActionName("delete")]
        public HttpResponseMessage DeleteObjective([FromUri]Guid id)
        {
            try
            {
                _objectiveService.DeleteObjective(id);
                return Request.CreateResponse(HttpStatusCode.OK, "Objective successfully deleted!");
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
                _objectiveService.Dispose();
            }

            base.Dispose(disposing);
        }
    }
}
