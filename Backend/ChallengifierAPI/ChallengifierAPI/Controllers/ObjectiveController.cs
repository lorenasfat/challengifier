using Business.DTOs;
using Business.Services.Interfaces;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ChallengifierAPI.Infrastructure.Session;

namespace ChallengifierAPI.Controllers
{
    public class ObjectiveController : ApiController
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

        [HttpPost]
        [ActionName("add")]
        public HttpResponseMessage AddObjective([FromBody]ObjectiveDto objective)
        {
            try
            {
                objective.Id = Guid.NewGuid();
                objective.UserId = SessionState.LoggedInUser == null ? _userService.getUserByUsername(objective.UserId).AspNetUserId : SessionState.LoggedInUser.AspNetUserId;
                _objectiveService.AddObjective(objective);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added an objective!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        [ActionName("all")]
        public HttpResponseMessage GetObjectives()
        {
            try
            {
                var objectives = _objectiveService.GetAllObjectives().ToList();
                if (objectives.Count == 0)
                    return Request.CreateResponse(HttpStatusCode.OK, "No entries yet!");

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
                return Request.CreateResponse(HttpStatusCode.OK, objective);
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
