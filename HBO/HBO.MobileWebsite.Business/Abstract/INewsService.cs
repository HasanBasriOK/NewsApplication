using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace HBO.MobileWebsite.Business.Abstract
{
    public interface INewsService : IEntityRepositoryService<News>
    {
        List<News> GetAllWithNewsType(Expression<Func<News, bool>> filter = null);
        News GetWithNewsType(Expression<Func<News, bool>> filter);
    }
}
