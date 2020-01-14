package cn.weiben.buildingshopping.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import static cn.weiben.buildingshopping.ui.adapter.ExpandableItemAdapter.TYPE_PERSON;

public class CategoriesBean implements MultiItemEntity {
    /**
     * id : 34
     * name : 电动工具
     * go : category
     * suppid : 13
     * cid : 34
     * url : supplier.php?go=category&amp;suppId=13&amp;id=34
     * cat_id : []
     */

    private String id;
    private String name;
    private String go;
    private int suppid;
    private String cid;
    private String url;
    private List<CategoriesBean> cat_id;

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

    public String getGo() {
        return go;
    }

    public void setGo(String go) {
        this.go = go;
    }

    public int getSuppid() {
        return suppid;
    }

    public void setSuppid(int suppid) {
        this.suppid = suppid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CategoriesBean> getCat_id() {
        return cat_id;
    }

    public void setCat_id(List<CategoriesBean> cat_id) {
        this.cat_id = cat_id;
    }

    @Override
    public int getItemType() {
        return TYPE_PERSON;
    }
}