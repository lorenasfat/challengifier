using Business.DTOs;
using Business.Services.Interfaces;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    public class ObjectiveController : ApiController
    {
        private readonly IObjectiveService _objectiveService;
        public ObjectiveController(IObjectiveService objectiveService)
        {
            _objectiveService = objectiveService;
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
