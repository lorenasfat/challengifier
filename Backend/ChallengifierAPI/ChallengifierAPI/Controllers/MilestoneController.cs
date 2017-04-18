using Business.DTOs;
using Business.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    public class MilestoneController : ApiController
    {
        private readonly MilestoneService _milestoneService;

        public MilestoneController(MilestoneService milestoneService)
        {
            _milestoneService = milestoneService;
        }

        [HttpPost]
        [ActionName("add")]
        public HttpResponseMessage AddMilestone(MilestoneDto milestone)
        {
            try
            {
                _milestoneService.AddMilestone(milestone);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added a milestone!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("delete")]
        public HttpResponseMessage DeleteMilestone(Guid id)
        {
            try
            {
                _milestoneService.DeleteMilestone(id);
                return Request.CreateResponse(HttpStatusCode.OK, "Successfully deleted a milestone!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("update")]
        public HttpResponseMessage UpdateMilestone(MilestoneDto milestone)
        {
            try
            {
                _milestoneService.UpdateMilestone(milestone);
                return Request.CreateResponse(HttpStatusCode.OK, "Successfully updated a miletone!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        [ActionName("all")]
        public HttpResponseMessage GetMilestones()
        {
            try
            {
                var milestones = _milestoneService.GetAllMilestones();
                if (milestones == null)
                    return Request.CreateResponse(HttpStatusCode.OK, "No entries!");

                return Request.CreateResponse(HttpStatusCode.OK, milestones);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        [ActionName("get")]
        public HttpResponseMessage GetMilestone(Guid id)
        {
            try
            {
                var milestone = _milestoneService.GetMilestoneById(id);
                if (milestone == null)
                {
                    return Request.CreateResponse(HttpStatusCode.NotFound);
                }
                return Request.CreateResponse(HttpStatusCode.OK, milestone);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}
