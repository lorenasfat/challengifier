using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ChallengifierAPI.Controllers
{
    [RoutePrefix("lorena")]
    public class Test2Controller : ApiController
    {
        [HttpGet]
        [ActionName("getAll")]
        public string Test()
        {
            return "hi";
        }

        [HttpGet]
        [ActionName("getSingle")]
        public string Test(int number, string a)
        {
            return "hi2";
        }
    }
}
