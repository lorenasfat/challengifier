using System.Linq;

namespace DataAccess.Repository.Interfaces
{
    public interface IBaseRepository<T, TKey> where T : class
    {
        IQueryable<T> All();
        T GetById(TKey id);
        T GetOrReloadById(TKey id);
        void Create(T item);
        void Delete(T item);
        void Save();
    }
}
