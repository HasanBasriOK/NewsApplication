using HBO.MobileWebsite.Business.Concrate.Manager;
using HBO.MobileWebsite.MvcWebUI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Data.Entity;

namespace HBO.MobileWebsite.MvcWebUI.Controllers
{
    public class NewsTypeController : Controller
    {
        NewsTypeManager _newsTypeManager = new NewsTypeManager();

        // GET: NewsType
        [HttpGet]
        public ActionResult ListNewsType()
        {
            var newsType = _newsTypeManager.GetAll();
            return View(newsType);
        }

        [HttpGet]
        public ActionResult DeleteNewsType(int id)
        {
            var newsType = new NewsTypeManager().Get(n => n.NewsTypeId == id);
            new NewsTypeManager().Delete(newsType);
            return RedirectToAction("ListNewsType");
        }

        [HttpGet]
        public ActionResult NewsTypeDetails(int id)
        {
            var model = _newsTypeManager.Get(m => m.NewsTypeId == id);
            return View(model);
        }


        //get ile ben sayfa istiyorum
        [HttpGet]
        public ActionResult AddNewsType(int id = 0)
        {
            NewsTypeAddViewModel model = new NewsTypeAddViewModel();

            if (id != 0)
            {
                var newsType = new NewsTypeManager().Get(n => n.NewsTypeId == id);
                model.NewsType = newsType;
            }
            return View(model);
        }

        [HttpPost]
        public ActionResult AddNewsType(NewsTypeAddViewModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    if (model.NewsType.NewsTypeId != 0)
                    {
                        var newsType = new NewsTypeManager().Get(n => n.NewsTypeId == model.NewsType.NewsTypeId);
                        newsType.NewsTypeId = model.NewsType.NewsTypeId;
                        newsType.NewsTypeName = model.NewsType.NewsTypeName;
                        newsType.NewsTypeDescription = model.NewsType.NewsTypeDescription;
                        new NewsTypeManager().Update(newsType);
                    }

                    else
                    {
                        new NewsTypeManager().Add(model.NewsType);
                    }

                    return RedirectToAction("ListNewsType");
                }
                catch (Exception exception)
                {
                    return RedirectToAction("Index","Error", new { message = exception.Message});
                }
            }
            else
            {
                return RedirectToAction("Index", "Error", new { message = "Model Yanlış"});
            }
        }
    }
}