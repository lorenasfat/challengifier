using Business.Components;
using Business.Components.Interface;
using Business.Services;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.Repository;
using DataAccess.Repository.Interfaces;
using DataAccess.UnitOfWork;
using Microsoft.Practices.Unity;
using System.Web.Http;
using Unity.WebApi;

namespace ChallengifierAPI
{
    public static class UnityConfig
    {
        public static void RegisterComponents()
        {
            var container = new UnityContainer();

            //Services
            container.RegisterType<IChallengeService, ChallengeService>();
            container.RegisterType<IMilestoneService, MilestoneService>();
            container.RegisterType<IObjectiveService, ObjectiveService>();
            container.RegisterType<IPictureService, PictureService>();
            container.RegisterType<IUserRatingService, UserRatingService>();
            container.RegisterType<IUserRankComponent, UserRankComponent>();
            container.RegisterType<IUserService, UserService>();
            container.RegisterType<IPlanningStepService, PlanningStepService>();


            //DB
            container.RegisterType<IUnitOfWork, UnitOfWork>(new HierarchicalLifetimeManager());
            container.RegisterType<ChallengifierEntities1>(new HierarchicalLifetimeManager());
            container.RegisterType<IChallengeRepository, ChallengeRepository>();
            container.RegisterType<IObjectiveRepository, ObjectiveRepository>();
            container.RegisterType<IMilestoneRepository, MilestoneRepository>();
            container.RegisterType<IPictureRepository, PictureRepository>();
            container.RegisterType<IPlanningStepRepository, PlanningStepRepository>();
            container.RegisterType<IUserRankRepository, UserRankRepository>();
            container.RegisterType<IUserRatingRepository, UserRatingRepository>();
            container.RegisterType<IUserRepository, UserRepository>();
            container.RegisterType<IObjectiveStatusRepository, ObjectiveStatusRepository>();

            GlobalConfiguration.Configuration.DependencyResolver = new UnityDependencyResolver(container);
        }
    }
}