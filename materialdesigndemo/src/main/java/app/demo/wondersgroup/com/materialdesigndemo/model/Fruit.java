package app.demo.wondersgroup.com.materialdesigndemo.model;

/**
 * Created by zhangwentao on 2016/12/21.
 */

public class Fruit {
    private String name;
    private int imgId;

    public Fruit(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
