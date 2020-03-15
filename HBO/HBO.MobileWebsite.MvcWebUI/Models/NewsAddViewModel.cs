using HBO.MobileWebsite.Entities.Concrate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HBO.MobileWebsite.MvcWebUI.Models
{
    public class NewsAddViewModel
    {
        public News News { get; set; }

        public NewsAddViewModel()
        {
            this.News = new News();
        }
    }
}