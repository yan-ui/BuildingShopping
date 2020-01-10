package cn.weiben.buildingshopping.model;

import java.util.List;

public class BonusBean {


    /**
     * page : 1
     * page_count : 1
     * bouns : [{"bonus_sn":"124579","order_id":"0","supplier_id":"0","type_name":"满300元送红包","type_money":"20.00","min_goods_amount":"300.00","use_start_date":"1558598400","use_end_date":"1561363200","status":"已过期","use_startdate":"2019-05-24","use_enddate":"2019-06-25","supplier_name":"网站自营"}]
     */

    private int page;
    private int page_count;
    private List<BounsBean> bouns;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public List<BounsBean> getBouns() {
        return bouns;
    }

    public void setBouns(List<BounsBean> bouns) {
        this.bouns = bouns;
    }

    public static class BounsBean {
        /**
         * bonus_sn : 124579
         * order_id : 0
         * supplier_id : 0
         * type_name : 满300元送红包
         * type_money : 20.00
         * min_goods_amount : 300.00
         * use_start_date : 1558598400
         * use_end_date : 1561363200
         * status : 已过期
         * use_startdate : 2019-05-24
         * use_enddate : 2019-06-25
         * supplier_name : 网站自营
         */

        private String bonus_sn;
        private String order_id;
        private String supplier_id;
        private String type_name;
        private String type_money;
        private String min_goods_amount;
        private String use_start_date;
        private String use_end_date;
        private String status;
        private String use_startdate;
        private String use_enddate;
        private String supplier_name;

        public String getBonus_sn() {
            return bonus_sn;
        }

        public void setBonus_sn(String bonus_sn) {
            this.bonus_sn = bonus_sn;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getType_money() {
            return type_money;
        }

        public void setType_money(String type_money) {
            this.type_money = type_money;
        }

        public String getMin_goods_amount() {
            return min_goods_amount;
        }

        public void setMin_goods_amount(String min_goods_amount) {
            this.min_goods_amount = min_goods_amount;
        }

        public String getUse_start_date() {
            return use_start_date;
        }

        public void setUse_start_date(String use_start_date) {
            this.use_start_date = use_start_date;
        }

        public String getUse_end_date() {
            return use_end_date;
        }

        public void setUse_end_date(String use_end_date) {
            this.use_end_date = use_end_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUse_startdate() {
            return use_startdate;
        }

        public void setUse_startdate(String use_startdate) {
            this.use_startdate = use_startdate;
        }

        public String getUse_enddate() {
            return use_enddate;
        }

        public void setUse_enddate(String use_enddate) {
            this.use_enddate = use_enddate;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }
    }
}
