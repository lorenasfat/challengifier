using ChallengifierAPI.App_Start;
using System;
using System.Web.Http;
using System.Web.Mvc;
using System.Web.Routing;
using ChallengifierAPI.Infrastructure.Authorization;

namespace ChallengifierAPI
{
    public class Global : System.Web.HttpApplication
    {
        protected void Application_Start(object sender, EventArgs e)
        {
            AreaRegistration.RegisterAllAreas();
            UnityConfig.RegisterComponents();
            GlobalConfiguration.Configuration.MapHttpAttributeRoutes();
            GlobalConfiguration.Configuration.Filters.Add(new BasicAuthenticationFilter());
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            GlobalConfiguration.Configuration.EnsureInitialized();
        }
    }
}