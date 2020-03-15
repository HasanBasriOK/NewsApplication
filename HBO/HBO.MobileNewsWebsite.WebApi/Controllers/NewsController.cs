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
    public class NewsController : ApiController
    {
        NewsManager _newsManager = new NewsManager();


        [HttpGet]
        public IHttpActionResult IncreaseDislikeCount(int newsId,string increaseVariable)
        {
            var news = _newsManager.Get(n=> n.NewsId == newsId);

            if (news != null)
            {
                if (increaseVariable == "goruntulenme")
                {
                    news.NewsViews += 1;
                }
                else if (increaseVariable == "begenme")
                {
                    news.NewsLikes += 1;
                }
                else if (increaseVariable == "begenmeme")
                {
                    news.NumberOfDislikes += 1;
                }
                _newsManager.Update(news);
                return Ok();
            }
            else
            {
                return NotFound();
            }
           
        }


        [HttpGet]
        public List<News> GetAllWithNewsType(String newsTypeName)
        {
            var news = _newsManager.GetAllWithNewsType(n => n.NewsType.NewsTypeName == newsTypeName);
            return news;
        }

        public News GetNewsById(int newsId)
        {
            var news = _newsManager.Get(x=> x.NewsId==newsId);
            news.NewsType = new NewsType() ;
            return news;
        }

        //[HttpPost]
        //public IHttpActionResult IncreaseLikeCount(int newsId)
        //{
        //    var news = _newsManager.Get(n => n.NewsId == newsId);
        //    if (news != null)
        //    {
        //        news.NewsLikes += 1;
        //        _newsManager.Update(news);
        //        return Ok();
        //    }

        //    else
        //    {
        //        return NotFound();
        //    }
           
        //}



        //[HttpPost]
        //public IHttpActionResult IncreaseReviewCount(int newsId)
        //{
        //    var news = _newsManager.Get(n=> n.NewsId == newsId);
        //    if (news != null)
        //    {
        //        news.NewsViews += 1;
        //        _newsManager.Update(news);
        //        return Ok();
        //    }

        //    else
        //    {
        //        return NotFound();
        //    }
        //}

    }
}
