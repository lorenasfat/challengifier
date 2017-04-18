using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System;

namespace DataAccess.Repository
{
    public class PictureRepository : BaseRepository<Picture, Guid>, IPictureRepository
    {
        public PictureRepository(ChallengifierEntities1 dbContext) : base(dbContext)
        {
        }
    }
}
