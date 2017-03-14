package usc.com.uscmaps.example1.shubham.parentchildconvo;

/**
 * Created by Shubham on 3/12/17.
 */

public class ChildrenGSON {
    private int id;
    private String name;
    private String image_url;

    public ChildrenGSON(int id, String name, String image_url){
        this.id = id;
        this.name = name;
        this.image_url = image_url;

    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    @Override
    public String toString() {
        return "ChildrenGSON{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
