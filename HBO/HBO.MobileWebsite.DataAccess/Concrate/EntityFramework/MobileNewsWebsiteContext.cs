using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HBO.MobileWebsite.DataAccess.Concrate.EntityFramework
{
    public class MobileNewsWebsiteContext : DbContext
    {
        public DbSet<News> News { get; set; }
        public DbSet<NewsType> NewTypes { get; set; }

        public MobileNewsWebsiteContext() : base("MobileNewsWebsite")
        {

        }
    }
}
