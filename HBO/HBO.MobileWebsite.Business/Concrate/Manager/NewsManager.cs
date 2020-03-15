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
    public class NewsManager : IEntityRepositoryService<News>, INewsService
    {
        EfNewsDal _newsDal = new EfNewsDal();

        public News Add(News entity)
        {
            return _newsDal.Add(entity);
        }

        public void Delete(News entity)
        {
            _newsDal.Delete(entity);
        }

        public News Get(Expression<Func<News, bool>> filter)
        {
            return _newsDal.Get(filter);
        }

        public List<News> GetAll(Expression<Func<News, bool>> filter = null)
        {
            return _newsDal.GetAll();
        }

        public List<News> GetAllWithNewsType(Expression<Func<News, bool>> filter = null)
        {
            return _newsDal.GetAllWithNewsType(filter);
        }

        public News GetWithNewsType(Expression<Func<News, bool>> filter)
        {
            return _newsDal.GetWithNewsType(filter);
        }

        public News Update(News entity)
        {
            return _newsDal.Update(entity);
        }
    }
}
