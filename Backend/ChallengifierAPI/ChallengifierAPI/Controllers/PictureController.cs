using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Business.DTOs;
using Business.Services.Interfaces;

namespace ChallengifierAPI.Controllers
{
    public class PictureController : ApiController
    {
        private readonly IPictureService _pictureService;
        public PictureController(IPictureService pictureService)
        {
            _pictureService = pictureService;
        }
        [HttpPost]
        [ActionName("add")]
        public HttpResponseMessage AddPicture(PictureDto picture)
        {
            try
            {
                _pictureService.AddPicture(picture);
                return Request.CreateResponse(HttpStatusCode.Created, "Successfully added a picture!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        [ActionName("delete")]
        public HttpResponseMessage DeletePicture(Guid id)
        {
            try
            {
                _pictureService.DeletePicture(id);
                return Request.CreateResponse(HttpStatusCode.OK, "Successfully deleted a picture!");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}
