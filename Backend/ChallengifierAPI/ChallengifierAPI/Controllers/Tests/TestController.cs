using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    public class TestController : ApiController
    {
        [HttpGet]
        public string Test()
        {
            return "hi";
        }

        [HttpGet]
        public string Test(int id)
        {
            return "hi2";
        }
    }
}
