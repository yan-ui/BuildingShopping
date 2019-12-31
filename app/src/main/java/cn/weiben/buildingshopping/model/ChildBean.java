package cn.weiben.buildingshopping.model;

import java.io.Serializable;
import java.util.List;

public class ChildBean implements Serializable {
    /**
     * id : 370
     * name : 电锤钻头
     * img : https://shop.shenghuopu.cn/mobile/images/201810/1540914745740354345.jpg
     * child : []
     */

    private String id;
    private String name;
    private String img;
    private List<ChildBean> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }
}