namespace HBO.MobileWebsite.DataAccess.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class FirtMigration : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.News",
                c => new
                    {
                        NewsId = c.Int(nullable: false, identity: true),
                        NewsImageUrl = c.String(),
                        NewsTitle = c.String(),
                        NewsContent = c.String(),
                        NewsTypeId = c.Int(nullable: false),
                        NewsLikes = c.Int(nullable: false),
                        NumberOfDislikes = c.Int(nullable: false),
                        NewsViews = c.Int(nullable: false),
                        NewsReleaseDate = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.NewsId)
                .ForeignKey("dbo.NewsTypes", t => t.NewsTypeId, cascadeDelete: true)
                .Index(t => t.NewsTypeId);
            
            CreateTable(
                "dbo.NewsTypes",
                c => new
                    {
                        NewsTypeId = c.Int(nullable: false, identity: true),
                        NewsTypeName = c.String(),
                        NewsTypeDescription = c.String(),
                    })
                .PrimaryKey(t => t.NewsTypeId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.News", "NewsTypeId", "dbo.NewsTypes");
            DropIndex("dbo.News", new[] { "NewsTypeId" });
            DropTable("dbo.NewsTypes");
            DropTable("dbo.News");
        }
    }
}
