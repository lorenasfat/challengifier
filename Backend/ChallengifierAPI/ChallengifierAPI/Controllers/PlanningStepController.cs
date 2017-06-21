using Business.DTOs;
using Business.Services.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    public class PlanningStepController : ApiController
    {
        private readonly IPlanningStepService _planningStepService;

        public PlanningStepController(IPlanningStepService planningStepService)
        {
            _planningStepService = planningStepService;
        }

        [HttpGet]
        [ActionName("get")]
        public HttpResponseMessage GetPlanningSteps(Guid id)
        {
            try
            {
                var planningSteps = _planningStepService.GetPlanningSteps(id);
                return Request.CreateResponse(HttpStatusCode.OK, planningSteps);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("add")]
        public HttpResponseMessage AddPlanningStep([FromBody]PlanningStepDto planningStep)
        {
            try
            {
                _planningStepService.AddPlanningStep(planningStep);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added an planning step!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpDelete]
        [ActionName("delete")]
        public HttpResponseMessage DeletePlanningStep([FromUri]Guid id)
        {
            try
            {
                _planningStepService.DeletePlanningStep(id);
                return Request.CreateResponse(HttpStatusCode.OK, "Planning Step successfully deleted!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}