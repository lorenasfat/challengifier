using System.Web.Mvc;

namespace Web.controllers
{
    public class OverviewController : Controller
    {
        // GET: Overview
        public ActionResult ChallengifierOverview()
        {
            return View("ChallengifierOverview");
        }
    }
}