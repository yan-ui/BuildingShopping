package cn.weiben.buildingshopping.model;

import java.util.List;

public class BuyGoods {


    /**
     * quick : 0
     * spec : ["9979"]
     * goods_id : 2331
     * number : 1
     * parent : 0
     */

    private int quick;
    private String goods_id;
    private String number;
    private int parent;
    private List<String> spec;

    public int getQuick() {
        return quick;
    }

    public void setQuick(int quick) {
        this.quick = quick;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<String> getSpec() {
        return spec;
    }

    public void setSpec(List<String> spec) {
        this.spec = spec;
    }
}
