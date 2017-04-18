using DataAccess.Entities;
using DataAccess.Repository.Interfaces;
using System.Data.Entity;
using System.Linq;

namespace DataAccess.Repository
{
    public abstract class BaseRepository<T, TKey> : IBaseRepository<T, TKey> where T : class
    {
        protected ChallengifierEntities1 DbContext;
        protected DbSet<T> DbSet;
        public BaseRepository(ChallengifierEntities1 dbContext)
        {
            DbContext = dbContext;
            DbSet = dbContext.Set<T>();
        }

        public IQueryable<T> GetById()
        {
            return DbSet.AsQueryable();
        }

        public IQueryable<T> All()
        {
            return DbSet.AsQueryable();
        }

        public T GetById(TKey id)
        {
            return DbSet.Find(id);
        }

        public T GetOrReloadById(TKey id)
        {
            var entity = DbSet.Find(id);
            if (entity != null && entity.GetType() == typeof(T))
            {
                DbContext.Entry(entity).State = EntityState.Detached;
                entity = DbSet.Find(id);
            }

            return entity;
        }

        public void Create(T item)
        {
            DbSet.Add(item);
        }

        public void Delete(T item)
        {
            DbSet.Remove(item);
        }

        public void Save()
        {
            DbContext.SaveChanges();
        }
    }
}
