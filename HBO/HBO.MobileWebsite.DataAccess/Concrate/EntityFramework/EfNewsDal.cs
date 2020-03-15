using HBO.MobileWebsite.DataAccess.Abstract;
using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity;

namespace HBO.MobileWebsite.DataAccess.Concrate.EntityFramework
{
    public class EfNewsDal : EfEntityRepositoryBase<News, MobileNewsWebsiteContext>, INewsDal
    {
        public List<News> GetAllWithNewsType(Expression<Func<News, bool>> filter = null)
        {
            List<News> news = new List<News>();
            using (MobileNewsWebsiteContext context = new MobileNewsWebsiteContext())
            {

                if (filter!=null)
                {
                    news = context.News.Include(n => n.NewsType).Where(filter).ToList();

                }
                else
                {
                    news = context.News.Include(n => n.NewsType).ToList();

                }
            }
            return news;
        }

        public News GetWithNewsType(Expression<Func<News, bool>> filter)
        {
            News news = new News();
            using (MobileNewsWebsiteContext context = new MobileNewsWebsiteContext())
            {
                news = context.News.Include(n=> n.NewsType).FirstOrDefault(filter);
            }

            return news;
        }
    }
}
