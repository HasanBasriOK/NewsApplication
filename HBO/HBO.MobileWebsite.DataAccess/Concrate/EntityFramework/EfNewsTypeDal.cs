using HBO.MobileWebsite.DataAccess.Abstract;
using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace HBO.MobileWebsite.DataAccess.Concrate.EntityFramework
{
    public class EfNewsTypeDal : EfEntityRepositoryBase<NewsType, MobileNewsWebsiteContext>, INewsTypeDal
    {

    }
}
