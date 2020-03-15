using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HBO.MobileWebsite.MvcWebUI.Models
{
    public class NewsTypeAddViewModel
    {
        public NewsType NewsType { get; set; }

        public NewsTypeAddViewModel()
        {
            this.NewsType = new NewsType();
        }
    }
}