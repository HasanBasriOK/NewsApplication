using HBO.MobileWebsite.Entities.Abstract;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HBO.MobileWebsite.Entities.Concrate
{
    public class News : IEntity
    {
        [DisplayName("Haber Id")]
        public int NewsId { get; set; }
        [DisplayName("Haber Resim URL si")]
        public String NewsImageUrl { get; set; }
        [DisplayName("Haber Başlığı")]
        public String NewsTitle { get; set; }
        [DisplayName("Haber İçeriği")]
        public String NewsContent { get; set; }
        [DisplayName("Haber Tür Id")]
        public int NewsTypeId { get; set; }
        public virtual NewsType NewsType { get; set; }
        [DisplayName("Haber Beğenme Sayısı")]
        public int NewsLikes { get; set; }
        [DisplayName("Haber Beğenmeme Sayısı")]
        public int NumberOfDislikes { get; set; }
        [DisplayName("Haber Görüntülenme Sayısı")]
        public int NewsViews { get; set; }
        [DisplayName("Haber Yayın Tarihi")]
        public DateTime NewsReleaseDate { get; set; }

    }
}
