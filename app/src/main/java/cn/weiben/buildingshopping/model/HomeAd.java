package cn.weiben.buildingshopping.model;

public class HomeAd {


    /**
     * ad_id : 31
     * position_id : 14
     * media_type : 0
     * ad_link : https://www.chinajcscw.com/mobile/supplier.php?suppId=13
     * ad_code : https://shop.shenghuopu.cn/mobile/data/afficheimg/1560535882554886701.png
     * ad_name : 手机端首页广告1-1
     * ad_width : 1
     * ad_height : 1
     * position_style : <table cellpadding="0" cellspacing="0">
     {foreach from=$ads item=ad}
     <tr><td>{$ad}</td></tr>
     {/foreach}
     </table>
     * rnd : 0.7030113895451756
     */

    private String ad_id;
    private String position_id;
    private String media_type;
    private String ad_link;
    private String ad_code;
    private String ad_name;
    private String ad_width;
    private String ad_height;
    private String position_style;
    private String rnd;

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getAd_link() {
        return ad_link;
    }

    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    public String getAd_code() {
        return ad_code;
    }

    public void setAd_code(String ad_code) {
        this.ad_code = ad_code;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_width() {
        return ad_width;
    }

    public void setAd_width(String ad_width) {
        this.ad_width = ad_width;
    }

    public String getAd_height() {
        return ad_height;
    }

    public void setAd_height(String ad_height) {
        this.ad_height = ad_height;
    }

    public String getPosition_style() {
        return position_style;
    }

    public void setPosition_style(String position_style) {
        this.position_style = position_style;
    }

    public String getRnd() {
        return rnd;
    }

    public void setRnd(String rnd) {
        this.rnd = rnd;
    }
}
