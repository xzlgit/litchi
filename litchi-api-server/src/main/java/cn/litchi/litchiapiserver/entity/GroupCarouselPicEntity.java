package cn.litchi.litchiapiserver.entity;

public class GroupCarouselPicEntity {
    private String title;
    private String pic;

    public GroupCarouselPicEntity(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "GroupCarouselPicEntity{" +
                "title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
