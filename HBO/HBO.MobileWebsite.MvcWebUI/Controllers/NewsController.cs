using HBO.MobileWebsite.Business.Concrate.Manager;
using HBO.MobileWebsite.MvcWebUI.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace HBO.MobileWebsite.MvcWebUI.Controllers
{
    public class NewsController : Controller
    {
        // GET: News
        [HttpGet]
        public ActionResult ListNews()
        {
            NewsManager newsManager = new NewsManager();
            var model = newsManager.GetAllWithNewsType();
            return View(model);
        }


        [HttpGet]
        public ActionResult DeleteNews(int id)
        {
            NewsManager newsManager = new NewsManager();
            var news = newsManager.Get(n => n.NewsId == id);
            newsManager.Delete(news);
            return RedirectToAction("ListNews");
        }


        [HttpGet]
        public ActionResult AddNews(int id = 0)
        {

            var model = new NewsAddViewModel();

            NewsTypeManager newsTypeManager = new NewsTypeManager();
            var newsTypes = newsTypeManager.GetAll();
            ViewBag.NewsTypes = newsTypes;
          
            if (id != 0)
            {
                var news = new NewsManager().Get(n => n.NewsId == id);
                model.News = news;
            }
            return View(model);
        }


        [HttpPost]
        public ActionResult AddNews(NewsAddViewModel model)
        {
            if (ModelState.IsValid)
            {
                if (model.News.NewsId != 0)
                {
                    //update
                    var news = new NewsManager().Get(n => n.NewsId == model.News.NewsId);
                    news.NewsContent = model.News.NewsContent;
                    news.NewsImageUrl = model.News.NewsImageUrl;
                    news.NewsTypeId = model.News.NewsTypeId;
                    news.NewsTitle = model.News.NewsTitle;
                    new NewsManager().Update(news);
                    SendMobileNotification(model.News.NewsTitle, "Yeni Haber",news.NewsId);
                }

                //insert
                else
                {
                    model.News.NewsReleaseDate = DateTime.Now;
                    var inserted= new NewsManager().Add(model.News);
                    SendMobileNotification(model.News.NewsTitle, "Yeni Haber",inserted.NewsId);
                }

                return RedirectToAction("ListNews");
            }

            else
            {
                return RedirectToAction("Index", "Error", new { message = "Model Yanlış" });
            }

        }

        public ActionResult DetailsNews(int id)
        {
            var model = new NewsManager().GetWithNewsType(x => x.NewsId == id);
            return View(model);
        }

        public ActionResult Test()
        {
            SendMobileNotification("Büşra", "Hasan", 2);
            return null;
        }

        [NonAction]
        public void SendMobileNotification(string title,string body,int newsId)
        {
            var request = (HttpWebRequest)WebRequest.Create("https://fcm.googleapis.com/fcm/send");

            var postData = "{ ";
            postData += "\"to\":\"/topics/HBOApp\",";
            postData += "\"notification\" : { ";
            postData += "\"body\" : ";
            postData += "\""+body+"\",";
            postData += "\"title\" : ";
            postData += "\""+title+"\"";
            postData += "},";
            postData += "\"data\" : {";
            postData += "\"body\" : \""+newsId+"\",";
            postData += "\"title\" : \"\"";
            postData += "}}";
            var data = Encoding.ASCII.GetBytes(postData);

            request.Headers.Add("Authorization", "key={authorizationkey}");
            request.Method = "POST";
            request.ContentType = "application/json";
            request.ContentLength = data.Length;

            using (var stream = request.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            var response = (HttpWebResponse)request.GetResponse();

            var responseString = new StreamReader(response.GetResponseStream()).ReadToEnd();
        }
    }
}

       