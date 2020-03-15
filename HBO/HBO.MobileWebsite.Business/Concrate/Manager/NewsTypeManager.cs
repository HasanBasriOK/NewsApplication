using HBO.MobileWebsite.Business.Abstract;
using HBO.MobileWebsite.DataAccess.Concrate.EntityFramework;
using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace HBO.MobileWebsite.Business.Concrate.Manager
{
    public class NewsTypeManager : IEntityRepositoryService<NewsType>, INewsTypeService
    {
        EfNewsTypeDal _newsTypeDal = new EfNewsTypeDal();

        public NewsType Add(NewsType entity)
        {
            return _newsTypeDal.Add(entity);
        }

        public void Delete(NewsType entity)
        {
            _newsTypeDal.Delete(entity);
        }

        public NewsType Get(Expression<Func<NewsType, bool>> filter)
        {
            return _newsTypeDal.Get(filter);
        }

        public List<NewsType> GetAll(Expression<Func<NewsType, bool>> filter = null)
        {
            return _newsTypeDal.GetAll();
        }

        public NewsType Update(NewsType entity)
        {
            return _newsTypeDal.Update(entity);
        }

        public static implicit operator NewsTypeManager(NewsManager v)
        {
            throw new NotImplementedException();
        }
    }
}
