using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Web.Routing;

namespace ChallengifierAPI.App_Start
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routeTable)
        {
            routeTable.MapHttpRoute("default", "api/{controller}/{action}/{id}", new { id = RouteParameter.Optional });
        }
    }
}