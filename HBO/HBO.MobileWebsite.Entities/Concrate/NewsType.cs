using HBO.MobileWebsite.Entities.Abstract;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HBO.MobileWebsite.Entities.Concrate
{
    public class NewsType : IEntity
    {
        [DisplayName("Haber Tip Id")]
        public int NewsTypeId { get; set; }
        [DisplayName("Haber Tip Ad")]
        public String NewsTypeName { get; set; }
        [DisplayName("Haber Tip Açıklama")]
        public String NewsTypeDescription { get; set; }
    }
}
