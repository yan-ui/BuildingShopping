package cn.weiben.buildingshopping.model;

import java.util.List;

public class CartBean implements Cloneable{


    /**
     * goods_list : [{"goods_list":[{"rec_id":"7648","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"129","goods_sn":"HL60018-HL60021","product_id":"148","goods_name":"沪林1A-4A、特级、精品锯片","market_price":"35.00","goods_price":"25.00","split_money":"0.00","goods_number":123,"goods_attr":"规格:特级  114mm[25] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"59","add_time":"1578356798","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","cat_id":"748","brand_id":"2","supplier_id":"0","pid":"129","subtotal":"375.00","goods_thumb":"https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg","is_cansel":"1"},{"rec_id":"7644","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"129","goods_sn":"HL60018-HL60021","product_id":"147","goods_name":"沪林1A-4A、特级、精品锯片","market_price":"30.00","goods_price":"20.00","split_money":"0.00","goods_number":32,"goods_attr":"规格:精品  114mm[20] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"58","add_time":"1578356658","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","cat_id":"748","brand_id":"2","supplier_id":"0","pid":"129","subtotal":"220.00","goods_thumb":"https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg","is_cansel":"1"},{"rec_id":"7643","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"129","goods_sn":"HL60018-HL60021","product_id":"144","goods_name":"沪林1A-4A、特级、精品锯片","market_price":"20.00","goods_price":"10.00","split_money":"0.00","goods_number":10,"goods_attr":"规格:1A  114mm[10] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"54","add_time":"1578356577","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","cat_id":"748","brand_id":"2","supplier_id":"0","pid":"129","subtotal":"100.00","goods_thumb":"https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg","is_cansel":"1"},{"rec_id":"7629","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"2331","goods_sn":"CLG002331","product_id":"7779","goods_name":"红色木工锯片4*40T齿 带孔  买一送一","market_price":"16.00","goods_price":"8.00","split_money":"0.00","goods_number":11,"goods_attr":"规格:红色木工锯片4*40T齿[8] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"9979","add_time":"1578348471","package_attr_id":"","cost_price":"0.00","promote_price":"8.00","exclusive":"-1","cat_id":"748","brand_id":"0","supplier_id":"0","pid":"2331","subtotal":"8.00","goods_thumb":"https://www.chinajcscw.com//images/201905/thumb_img/2331_thumb_G_1557860817274.jpg","is_cansel":"1"}],"supplier_name":"网站自营"}]
     * total : {"goods_price":"3903.00","market_price":"5641.00","saving":"1738.00","save_rate":"31%","goods_amount":3903,"real_goods_count":24,"virtual_goods_count":0}
     * shopping_money : 3903.00
     * market_price_desc : 比市场价 5641.00 节省了 1738.00 (31%)
     * show_goods_thumb : 3
     * show_goods_attribute : 1
     * fittings_list : []
     */

    private TotalBean total;
    private String shopping_money;
    private String market_price_desc;
    private String show_goods_thumb;
    private String show_goods_attribute;
    private List<GoodsListBeanX> goods_list;
    private List fittings_list;

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public String getShopping_money() {
        return shopping_money;
    }

    public void setShopping_money(String shopping_money) {
        this.shopping_money = shopping_money;
    }

    public String getMarket_price_desc() {
        return market_price_desc;
    }

    public void setMarket_price_desc(String market_price_desc) {
        this.market_price_desc = market_price_desc;
    }

    public String getShow_goods_thumb() {
        return show_goods_thumb;
    }

    public void setShow_goods_thumb(String show_goods_thumb) {
        this.show_goods_thumb = show_goods_thumb;
    }

    public String getShow_goods_attribute() {
        return show_goods_attribute;
    }

    public void setShow_goods_attribute(String show_goods_attribute) {
        this.show_goods_attribute = show_goods_attribute;
    }

    public List<GoodsListBeanX> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBeanX> goods_list) {
        this.goods_list = goods_list;
    }

    public List getFittings_list() {
        return fittings_list;
    }

    public void setFittings_list(List fittings_list) {
        this.fittings_list = fittings_list;
    }

    public static class TotalBean {
        /**
         * goods_price : 3903.00
         * market_price : 5641.00
         * saving : 1738.00
         * save_rate : 31%
         * goods_amount : 3903
         * real_goods_count : 24
         * virtual_goods_count : 0
         */

        private String goods_price;
        private String market_price;
        private String saving;
        private String save_rate;
        private String goods_amount;
        private int real_goods_count;
        private int virtual_goods_count;

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getSaving() {
            return saving;
        }

        public void setSaving(String saving) {
            this.saving = saving;
        }

        public String getSave_rate() {
            return save_rate;
        }

        public void setSave_rate(String save_rate) {
            this.save_rate = save_rate;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public int getReal_goods_count() {
            return real_goods_count;
        }

        public void setReal_goods_count(int real_goods_count) {
            this.real_goods_count = real_goods_count;
        }

        public int getVirtual_goods_count() {
            return virtual_goods_count;
        }

        public void setVirtual_goods_count(int virtual_goods_count) {
            this.virtual_goods_count = virtual_goods_count;
        }
    }

    public static class GoodsListBeanX implements Cloneable{
        /**
         * goods_list : [{"rec_id":"7648","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"129","goods_sn":"HL60018-HL60021","product_id":"148","goods_name":"沪林1A-4A、特级、精品锯片","market_price":"35.00","goods_price":"25.00","split_money":"0.00","goods_number":123,"goods_attr":"规格:特级  114mm[25] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"59","add_time":"1578356798","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","cat_id":"748","brand_id":"2","supplier_id":"0","pid":"129","subtotal":"375.00","goods_thumb":"https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg","is_cansel":"1"},{"rec_id":"7644","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"129","goods_sn":"HL60018-HL60021","product_id":"147","goods_name":"沪林1A-4A、特级、精品锯片","market_price":"30.00","goods_price":"20.00","split_money":"0.00","goods_number":32,"goods_attr":"规格:精品  114mm[20] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"58","add_time":"1578356658","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","cat_id":"748","brand_id":"2","supplier_id":"0","pid":"129","subtotal":"220.00","goods_thumb":"https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg","is_cansel":"1"},{"rec_id":"7643","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"129","goods_sn":"HL60018-HL60021","product_id":"144","goods_name":"沪林1A-4A、特级、精品锯片","market_price":"20.00","goods_price":"10.00","split_money":"0.00","goods_number":10,"goods_attr":"规格:1A  114mm[10] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"54","add_time":"1578356577","package_attr_id":"","cost_price":"0.00","promote_price":"0.00","exclusive":"-1","cat_id":"748","brand_id":"2","supplier_id":"0","pid":"129","subtotal":"100.00","goods_thumb":"https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg","is_cansel":"1"},{"rec_id":"7629","user_id":"0","session_id":"66e17f41-b6db-4e09-a07e-d7ae123f","goods_id":"2331","goods_sn":"CLG002331","product_id":"7779","goods_name":"红色木工锯片4*40T齿 带孔  买一送一","market_price":"16.00","goods_price":"8.00","split_money":"0.00","goods_number":11,"goods_attr":"规格:红色木工锯片4*40T齿[8] \n","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"0","can_handsel":"0","goods_attr_id":"9979","add_time":"1578348471","package_attr_id":"","cost_price":"0.00","promote_price":"8.00","exclusive":"-1","cat_id":"748","brand_id":"0","supplier_id":"0","pid":"2331","subtotal":"8.00","goods_thumb":"https://www.chinajcscw.com//images/201905/thumb_img/2331_thumb_G_1557860817274.jpg","is_cansel":"1"}]
         * supplier_name : 网站自营
         */

        private String supplier_name;
        private List<GoodsListBean> goods_list;
        private boolean isSelect_shop;      //店铺是否在购物车中被选中


        public GoodsListBeanX clone() {
            GoodsListBeanX o = null;
            try {
                o = (GoodsListBeanX) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return o;
        }

        public boolean getIsSelect_shop() {
            return isSelect_shop;
        }

        public void setIsSelect_shop(boolean select_shop) {
            isSelect_shop = select_shop;
        }


        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * rec_id : 7648
             * user_id : 0
             * session_id : 66e17f41-b6db-4e09-a07e-d7ae123f
             * goods_id : 129
             * goods_sn : HL60018-HL60021
             * product_id : 148
             * goods_name : 沪林1A-4A、特级、精品锯片
             * market_price : 35.00
             * goods_price : 25.00
             * split_money : 0.00
             * goods_number : 123
             * goods_attr : 规格:特级  114mm[25]
             * is_real : 1
             * extension_code :
             * parent_id : 0
             * rec_type : 0
             * is_gift : 0
             * is_shipping : 0
             * can_handsel : 0
             * goods_attr_id : 59
             * add_time : 1578356798
             * package_attr_id :
             * cost_price : 0.00
             * promote_price : 0.00
             * exclusive : -1
             * cat_id : 748
             * brand_id : 2
             * supplier_id : 0
             * pid : 129
             * subtotal : 375.00
             * goods_thumb : https://www.chinajcscw.com//images/201902/thumb_img/129_thumb_G_1550200369817.jpg
             * is_cansel : 1
             */

            private String rec_id;
            private String user_id;
            private String session_id;
            private String goods_id;
            private String goods_sn;
            private String product_id;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String split_money;
            private String goods_number;
            private String goods_attr;
            private String is_real;
            private String extension_code;
            private String parent_id;
            private String rec_type;
            private String is_gift;
            private String is_shipping;
            private String can_handsel;
            private String goods_attr_id;
            private String add_time;
            private String package_attr_id;
            private String cost_price;
            private String promote_price;
            private String exclusive;
            private String cat_id;
            private String brand_id;
            private String supplier_id;
            private String pid;
            private String subtotal;
            private String goods_thumb;
            private String is_cansel;
            private boolean isSelect;        //商品是否在购物车中被选中

            public boolean getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(boolean select) {
                isSelect = select;
            }


            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSession_id() {
                return session_id;
            }

            public void setSession_id(String session_id) {
                this.session_id = session_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getSplit_money() {
                return split_money;
            }

            public void setSplit_money(String split_money) {
                this.split_money = split_money;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(String goods_attr) {
                this.goods_attr = goods_attr;
            }

            public String getIs_real() {
                return is_real;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public String getExtension_code() {
                return extension_code;
            }

            public void setExtension_code(String extension_code) {
                this.extension_code = extension_code;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getRec_type() {
                return rec_type;
            }

            public void setRec_type(String rec_type) {
                this.rec_type = rec_type;
            }

            public String getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(String is_gift) {
                this.is_gift = is_gift;
            }

            public String getIs_shipping() {
                return is_shipping;
            }

            public void setIs_shipping(String is_shipping) {
                this.is_shipping = is_shipping;
            }

            public String getCan_handsel() {
                return can_handsel;
            }

            public void setCan_handsel(String can_handsel) {
                this.can_handsel = can_handsel;
            }

            public String getGoods_attr_id() {
                return goods_attr_id;
            }

            public void setGoods_attr_id(String goods_attr_id) {
                this.goods_attr_id = goods_attr_id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPackage_attr_id() {
                return package_attr_id;
            }

            public void setPackage_attr_id(String package_attr_id) {
                this.package_attr_id = package_attr_id;
            }

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getPromote_price() {
                return promote_price;
            }

            public void setPromote_price(String promote_price) {
                this.promote_price = promote_price;
            }

            public String getExclusive() {
                return exclusive;
            }

            public void setExclusive(String exclusive) {
                this.exclusive = exclusive;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getSubtotal() {
                return subtotal;
            }

            public void setSubtotal(String subtotal) {
                this.subtotal = subtotal;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getIs_cansel() {
                return is_cansel;
            }

            public void setIs_cansel(String is_cansel) {
                this.is_cansel = is_cansel;
            }
        }
    }
}
