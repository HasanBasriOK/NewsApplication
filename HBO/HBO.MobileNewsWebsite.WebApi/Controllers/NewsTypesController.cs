using HBO.MobileWebsite.Business.Concrate.Manager;
using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace HBO.MobileNewsWebsite.WebApi.Controllers
{
    public class NewsTypesController : ApiController
    {
        NewsTypeManager _newsTypeManager = new NewsTypeManager();

        [HttpGet]
        public List<NewsType> Get()
        {
            var newsType = _newsTypeManager.GetAll();
            return newsType;
        }

    }
}
