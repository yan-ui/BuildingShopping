package cn.weiben.buildingshopping.model;

import java.util.List;

public class CartPayBean {


    /**
     * cart_goods : [{"rec_id":"7714","user_id":"1711","goods_id":"2333","goods_name":"红色木工锯片4*40齿 买一送一","goods_sn":"CLG002333","goods_number":"2","market_price":"16.00","goods_price":"8.00","goods_attr":"规格:红色木工锯片4*40齿[8] \n","is_real":"1","extension_code":"","parent_id":"0","is_gift":"0","is_shipping":"0","package_attr_id":"","subtotal":"16.00","supplier_id":"0","seller":"网站自营","formated_market_price":"16.00","formated_goods_price":"8.00","formated_subtotal":"16.00","goods_thumb":"https://www.chinajcscw.com//images/201905/thumb_img/2333_thumb_G_1557860958453.jpg"}]
     * shipping_list : [{"shipping_id":"72","shipping_code":"sto_express","shipping_name":"默认快递","shipping_desc":"由商家选择合作快递为您配送：","insure":"0","support_cod":"0","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:1:\"6\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:1:\"3\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:0:\"\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","support_pickup":"0"},{"shipping_id":"72","shipping_code":"sto_express","shipping_name":"默认快递","shipping_desc":"由商家选择合作快递为您配送：","insure":"0","support_cod":"0","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:1:\"6\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:1:\"2\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:0:\"\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","support_pickup":"0"},{"shipping_id":"72","shipping_code":"sto_express","shipping_name":"默认快递","shipping_desc":"由商家选择合作快递为您配送：","insure":"0","support_cod":"0","configure":"a:5:{i:0;a:2:{s:4:\"name\";s:8:\"item_fee\";s:5:\"value\";s:2:\"15\";}i:1;a:2:{s:4:\"name\";s:8:\"base_fee\";s:5:\"value\";s:2:\"20\";}i:2;a:2:{s:4:\"name\";s:8:\"step_fee\";s:5:\"value\";s:2:\"12\";}i:3;a:2:{s:4:\"name\";s:10:\"free_money\";s:5:\"value\";s:0:\"\";}i:4;a:2:{s:4:\"name\";s:16:\"fee_compute_mode\";s:5:\"value\";s:9:\"by_weight\";}}","support_pickup":"0"}]
     * order : {"shipping_id":72,"pay_id":1,"pack_id":0,"card_id":0,"bonus":0,"integral":0,"surplus":0,"shipping_pay":[72],"extension_code":"","pay_code":"alipay"}
     * total : {"real_goods_count":1,"gift_amount":0,"goods_price":16,"market_price":32,"discount":null,"pack_fee":0,"card_fee":0,"shipping_fee":6,"shipping_insure":0,"integral_money":0,"bonus":0,"surplus":0,"cod_fee":0,"pay_fee":0,"tax":0,"saving":16,"save_rate":"50%","goods_price_formated":"16.00","market_price_formated":"32.00","saving_formated":"16.00","discount_formated":"0.00","tax_formated":"0.00","pack_fee_formated":"0.00","card_fee_formated":"0.00","bonus_formated":"0.00","supplier_shipping":[{"supplier_name":"本网站","goods_number":2,"shipping_fee":"6","formated_shipping_fee":"6.00"}],"supplier_goodsnumber":[2],"goods_price_supplier":[16],"shipping_fee_formated":"6.00","amount":22,"surplus_formated":"0.00","integral":0,"integral_formated":"0.00","pay_fee_formated":"0.00","amount_formated":"22.00","will_get_integral":0,"will_get_bonus":"0.00","formated_goods_price":"16.00","formated_market_price":"32.00","formated_saving":"16.00"}
     * payment_list : [{"pay_id":"1","pay_code":"alipay","pay_name":"支付宝","pay_fee":"0","pay_desc":"支付宝支付","pay_config":"a:4:{i:0;a:3:{s:4:\"name\";s:14:\"alipay_account\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:16:\"413266736@qq.com\";}i:1;a:3:{s:4:\"name\";s:10:\"alipay_key\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:32:\"j011csm6v7nrotp4y4hkvv8bnwxm493h\";}i:2;a:3:{s:4:\"name\";s:14:\"alipay_partner\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:16:\"2088531703603781\";}i:3;a:3:{s:4:\"name\";s:17:\"alipay_pay_method\";s:4:\"type\";s:6:\"select\";s:5:\"value\";s:1:\"2\";}}","is_cod":"0","format_pay_fee":""},{"pay_id":"6","pay_code":"weixin","pay_name":"微信支付","pay_fee":"0","pay_desc":"微信支付","pay_config":"a:4:{i:0;a:3:{s:4:\"name\";s:5:\"appId\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:18:\"wxd6189924472805c0\";}i:1;a:3:{s:4:\"name\";s:9:\"appSecret\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:32:\"90c4d74bdd461933beaeb0cd1d4df957\";}i:2;a:3:{s:4:\"name\";s:9:\"partnerId\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:10:\"1544259091\";}i:3;a:3:{s:4:\"name\";s:10:\"partnerKey\";s:4:\"type\";s:4:\"text\";s:5:\"value\";s:32:\"310c02c062974ea773e74decb33a371x\";}}","is_cod":"0","format_pay_fee":""},{"pay_id":"3","pay_code":"balance","pay_name":"余额支付","pay_fee":"0","pay_desc":"使用帐户余额支付。只有会员才能使用，通过设置信用额度，可以透支。","pay_config":"a:0:{}","is_cod":"0","format_pay_fee":""}]
     * shopping_money : 16.00
     * market_price_desc : 比市场价 32.00 节省了 16.00 (50%)
     * order_info : {"shipping_id":72,"pay_id":1,"pack_id":0,"card_id":0,"bonus":0,"integral":0,"surplus":0,"shipping_pay":[72],"extension_code":"","pay_code":"alipay"}
     * currency_format : %s
     * integral_scale : 0.1
     * step : checkout
     */

    private OrderBean order;
    private TotalBean total;
    private String shopping_money;
    private String market_price_desc;
    private OrderInfoBean order_info;
    private String currency_format;
    private String integral_scale;
    private String step;
    private List<CartGoodsBean> cart_goods;
    private List<ShippingListBean> shipping_list;
    private List<PaymentListBean> payment_list;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

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

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public String getCurrency_format() {
        return currency_format;
    }

    public void setCurrency_format(String currency_format) {
        this.currency_format = currency_format;
    }

    public String getIntegral_scale() {
        return integral_scale;
    }

    public void setIntegral_scale(String integral_scale) {
        this.integral_scale = integral_scale;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<CartGoodsBean> getCart_goods() {
        return cart_goods;
    }

    public void setCart_goods(List<CartGoodsBean> cart_goods) {
        this.cart_goods = cart_goods;
    }

    public List<ShippingListBean> getShipping_list() {
        return shipping_list;
    }

    public void setShipping_list(List<ShippingListBean> shipping_list) {
        this.shipping_list = shipping_list;
    }

    public List<PaymentListBean> getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(List<PaymentListBean> payment_list) {
        this.payment_list = payment_list;
    }

    public static class OrderBean {
        /**
         * shipping_id : 72
         * pay_id : 1
         * pack_id : 0
         * card_id : 0
         * bonus : 0
         * integral : 0
         * surplus : 0
         * shipping_pay : [72]
         * extension_code :
         * pay_code : alipay
         */

        private String shipping_id;
        private String pay_id;
        private String pack_id;
        private String card_id;
        private String bonus;
        private String integral;
        private String surplus;
        private String extension_code;
        private String pay_code;
        private List<String> shipping_pay;

        public String getShipping_id() {
            return shipping_id;
        }

        public void setShipping_id(String shipping_id) {
            this.shipping_id = shipping_id;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getPack_id() {
            return pack_id;
        }

        public void setPack_id(String pack_id) {
            this.pack_id = pack_id;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getExtension_code() {
            return extension_code;
        }

        public void setExtension_code(String extension_code) {
            this.extension_code = extension_code;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public List<String> getShipping_pay() {
            return shipping_pay;
        }

        public void setShipping_pay(List<String> shipping_pay) {
            this.shipping_pay = shipping_pay;
        }
    }

    public static class TotalBean {
        /**
         * real_goods_count : 1
         * gift_amount : 0
         * goods_price : 16
         * market_price : 32
         * discount : null
         * pack_fee : 0
         * card_fee : 0
         * shipping_fee : 6
         * shipping_insure : 0
         * integral_money : 0
         * bonus : 0
         * surplus : 0
         * cod_fee : 0
         * pay_fee : 0
         * tax : 0
         * saving : 16
         * save_rate : 50%
         * goods_price_formated : 16.00
         * market_price_formated : 32.00
         * saving_formated : 16.00
         * discount_formated : 0.00
         * tax_formated : 0.00
         * pack_fee_formated : 0.00
         * card_fee_formated : 0.00
         * bonus_formated : 0.00
         * supplier_shipping : [{"supplier_name":"本网站","goods_number":2,"shipping_fee":"6","formated_shipping_fee":"6.00"}]
         * supplier_goodsnumber : [2]
         * goods_price_supplier : [16]
         * shipping_fee_formated : 6.00
         * amount : 22
         * surplus_formated : 0.00
         * integral : 0
         * integral_formated : 0.00
         * pay_fee_formated : 0.00
         * amount_formated : 22.00
         * will_get_integral : 0
         * will_get_bonus : 0.00
         * formated_goods_price : 16.00
         * formated_market_price : 32.00
         * formated_saving : 16.00
         */

        private String real_goods_count;
        private String gift_amount;
        private String goods_price;
        private String market_price;
        private Object discount;
        private String pack_fee;
        private String card_fee;
        private String shipping_fee;
        private String shipping_insure;
        private String integral_money;
        private String bonus;
        private String surplus;
        private String cod_fee;
        private String pay_fee;
        private String tax;
        private String saving;
        private String save_rate;
        private String goods_price_formated;
        private String market_price_formated;
        private String saving_formated;
        private String discount_formated;
        private String tax_formated;
        private String pack_fee_formated;
        private String card_fee_formated;
        private String bonus_formated;
        private String shipping_fee_formated;
        private String amount;
        private String surplus_formated;
        private String integral;
        private String integral_formated;
        private String pay_fee_formated;
        private String amount_formated;
        private String will_get_integral;
        private String will_get_bonus;
        private String formated_goods_price;
        private String formated_market_price;
        private String formated_saving;
        private List<SupplierShippingBean> supplier_shipping;
        private List<String> supplier_goodsnumber;
        private List<String> goods_price_supplier;

        public String getReal_goods_count() {
            return real_goods_count;
        }

        public void setReal_goods_count(String real_goods_count) {
            this.real_goods_count = real_goods_count;
        }

        public String getGift_amount() {
            return gift_amount;
        }

        public void setGift_amount(String gift_amount) {
            this.gift_amount = gift_amount;
        }

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

        public Object getDiscount() {
            return discount;
        }

        public void setDiscount(Object discount) {
            this.discount = discount;
        }

        public String getPack_fee() {
            return pack_fee;
        }

        public void setPack_fee(String pack_fee) {
            this.pack_fee = pack_fee;
        }

        public String getCard_fee() {
            return card_fee;
        }

        public void setCard_fee(String card_fee) {
            this.card_fee = card_fee;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getShipping_insure() {
            return shipping_insure;
        }

        public void setShipping_insure(String shipping_insure) {
            this.shipping_insure = shipping_insure;
        }

        public String getIntegral_money() {
            return integral_money;
        }

        public void setIntegral_money(String integral_money) {
            this.integral_money = integral_money;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getCod_fee() {
            return cod_fee;
        }

        public void setCod_fee(String cod_fee) {
            this.cod_fee = cod_fee;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
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

        public String getGoods_price_formated() {
            return goods_price_formated;
        }

        public void setGoods_price_formated(String goods_price_formated) {
            this.goods_price_formated = goods_price_formated;
        }

        public String getMarket_price_formated() {
            return market_price_formated;
        }

        public void setMarket_price_formated(String market_price_formated) {
            this.market_price_formated = market_price_formated;
        }

        public String getSaving_formated() {
            return saving_formated;
        }

        public void setSaving_formated(String saving_formated) {
            this.saving_formated = saving_formated;
        }

        public String getDiscount_formated() {
            return discount_formated;
        }

        public void setDiscount_formated(String discount_formated) {
            this.discount_formated = discount_formated;
        }

        public String getTax_formated() {
            return tax_formated;
        }

        public void setTax_formated(String tax_formated) {
            this.tax_formated = tax_formated;
        }

        public String getPack_fee_formated() {
            return pack_fee_formated;
        }

        public void setPack_fee_formated(String pack_fee_formated) {
            this.pack_fee_formated = pack_fee_formated;
        }

        public String getCard_fee_formated() {
            return card_fee_formated;
        }

        public void setCard_fee_formated(String card_fee_formated) {
            this.card_fee_formated = card_fee_formated;
        }

        public String getBonus_formated() {
            return bonus_formated;
        }

        public void setBonus_formated(String bonus_formated) {
            this.bonus_formated = bonus_formated;
        }

        public String getShipping_fee_formated() {
            return shipping_fee_formated;
        }

        public void setShipping_fee_formated(String shipping_fee_formated) {
            this.shipping_fee_formated = shipping_fee_formated;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getSurplus_formated() {
            return surplus_formated;
        }

        public void setSurplus_formated(String surplus_formated) {
            this.surplus_formated = surplus_formated;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIntegral_formated() {
            return integral_formated;
        }

        public void setIntegral_formated(String integral_formated) {
            this.integral_formated = integral_formated;
        }

        public String getPay_fee_formated() {
            return pay_fee_formated;
        }

        public void setPay_fee_formated(String pay_fee_formated) {
            this.pay_fee_formated = pay_fee_formated;
        }

        public String getAmount_formated() {
            return amount_formated;
        }

        public void setAmount_formated(String amount_formated) {
            this.amount_formated = amount_formated;
        }

        public String getWill_get_integral() {
            return will_get_integral;
        }

        public void setWill_get_integral(String will_get_integral) {
            this.will_get_integral = will_get_integral;
        }

        public String getWill_get_bonus() {
            return will_get_bonus;
        }

        public void setWill_get_bonus(String will_get_bonus) {
            this.will_get_bonus = will_get_bonus;
        }

        public String getFormated_goods_price() {
            return formated_goods_price;
        }

        public void setFormated_goods_price(String formated_goods_price) {
            this.formated_goods_price = formated_goods_price;
        }

        public String getFormated_market_price() {
            return formated_market_price;
        }

        public void setFormated_market_price(String formated_market_price) {
            this.formated_market_price = formated_market_price;
        }

        public String getFormated_saving() {
            return formated_saving;
        }

        public void setFormated_saving(String formated_saving) {
            this.formated_saving = formated_saving;
        }

        public List<SupplierShippingBean> getSupplier_shipping() {
            return supplier_shipping;
        }

        public void setSupplier_shipping(List<SupplierShippingBean> supplier_shipping) {
            this.supplier_shipping = supplier_shipping;
        }

        public List<String> getSupplier_goodsnumber() {
            return supplier_goodsnumber;
        }

        public void setSupplier_goodsnumber(List<String> supplier_goodsnumber) {
            this.supplier_goodsnumber = supplier_goodsnumber;
        }

        public List<String> getGoods_price_supplier() {
            return goods_price_supplier;
        }

        public void setGoods_price_supplier(List<String> goods_price_supplier) {
            this.goods_price_supplier = goods_price_supplier;
        }

        public static class SupplierShippingBean {
            /**
             * supplier_name : 本网站
             * goods_number : 2
             * shipping_fee : 6
             * formated_shipping_fee : 6.00
             */

            private String supplier_name;
            private String goods_number;
            private String shipping_fee;
            private String formated_shipping_fee;

            public String getSupplier_name() {
                return supplier_name;
            }

            public void setSupplier_name(String supplier_name) {
                this.supplier_name = supplier_name;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getShipping_fee() {
                return shipping_fee;
            }

            public void setShipping_fee(String shipping_fee) {
                this.shipping_fee = shipping_fee;
            }

            public String getFormated_shipping_fee() {
                return formated_shipping_fee;
            }

            public void setFormated_shipping_fee(String formated_shipping_fee) {
                this.formated_shipping_fee = formated_shipping_fee;
            }
        }
    }

    public static class OrderInfoBean {
        /**
         * shipping_id : 72
         * pay_id : 1
         * pack_id : 0
         * card_id : 0
         * bonus : 0
         * integral : 0
         * surplus : 0
         * shipping_pay : [72]
         * extension_code :
         * pay_code : alipay
         */

        private String shipping_id;
        private String pay_id;
        private String pack_id;
        private String card_id;
        private String bonus;
        private String integral;
        private String surplus;
        private String extension_code;
        private String pay_code;
        private List<String> shipping_pay;

        public String getShipping_id() {
            return shipping_id;
        }

        public void setShipping_id(String shipping_id) {
            this.shipping_id = shipping_id;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getPack_id() {
            return pack_id;
        }

        public void setPack_id(String pack_id) {
            this.pack_id = pack_id;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getStringegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getExtension_code() {
            return extension_code;
        }

        public void setExtension_code(String extension_code) {
            this.extension_code = extension_code;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public List<String> getShipping_pay() {
            return shipping_pay;
        }

        public void setShipping_pay(List<String> shipping_pay) {
            this.shipping_pay = shipping_pay;
        }
    }

    public static class CartGoodsBean {
        /**
         * rec_id : 7714
         * user_id : 1711
         * goods_id : 2333
         * goods_name : 红色木工锯片4*40齿 买一送一
         * goods_sn : CLG002333
         * goods_number : 2
         * market_price : 16.00
         * goods_price : 8.00
         * goods_attr : 规格:红色木工锯片4*40齿[8]
         * is_real : 1
         * extension_code :
         * parent_id : 0
         * is_gift : 0
         * is_shipping : 0
         * package_attr_id :
         * subtotal : 16.00
         * supplier_id : 0
         * seller : 网站自营
         * formated_market_price : 16.00
         * formated_goods_price : 8.00
         * formated_subtotal : 16.00
         * goods_thumb : https://www.chinajcscw.com//images/201905/thumb_img/2333_thumb_G_1557860958453.jpg
         */

        private String rec_id;
        private String user_id;
        private String goods_id;
        private String goods_name;
        private String goods_sn;
        private String goods_number;
        private String market_price;
        private String goods_price;
        private String goods_attr;
        private String is_real;
        private String extension_code;
        private String parent_id;
        private String is_gift;
        private String is_shipping;
        private String package_attr_id;
        private String subtotal;
        private String supplier_id;
        private String seller;
        private String formated_market_price;
        private String formated_goods_price;
        private String formated_subtotal;
        private String goods_thumb;

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

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
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

        public String getPackage_attr_id() {
            return package_attr_id;
        }

        public void setPackage_attr_id(String package_attr_id) {
            this.package_attr_id = package_attr_id;
        }

        public String getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(String subtotal) {
            this.subtotal = subtotal;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getFormated_market_price() {
            return formated_market_price;
        }

        public void setFormated_market_price(String formated_market_price) {
            this.formated_market_price = formated_market_price;
        }

        public String getFormated_goods_price() {
            return formated_goods_price;
        }

        public void setFormated_goods_price(String formated_goods_price) {
            this.formated_goods_price = formated_goods_price;
        }

        public String getFormated_subtotal() {
            return formated_subtotal;
        }

        public void setFormated_subtotal(String formated_subtotal) {
            this.formated_subtotal = formated_subtotal;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }
    }

    public static class ShippingListBean {
        /**
         * shipping_id : 72
         * shipping_code : sto_express
         * shipping_name : 默认快递
         * shipping_desc : 由商家选择合作快递为您配送：
         * insure : 0
         * support_cod : 0
         * configure : a:5:{i:0;a:2:{s:4:"name";s:8:"item_fee";s:5:"value";s:2:"15";}i:1;a:2:{s:4:"name";s:8:"base_fee";s:5:"value";s:1:"6";}i:2;a:2:{s:4:"name";s:8:"step_fee";s:5:"value";s:1:"3";}i:3;a:2:{s:4:"name";s:10:"free_money";s:5:"value";s:0:"";}i:4;a:2:{s:4:"name";s:16:"fee_compute_mode";s:5:"value";s:9:"by_weight";}}
         * support_pickup : 0
         */

        private String shipping_id;
        private String shipping_code;
        private String shipping_name;
        private String shipping_desc;
        private String insure;
        private String support_cod;
        private String configure;
        private String support_pickup;

        public String getShipping_id() {
            return shipping_id;
        }

        public void setShipping_id(String shipping_id) {
            this.shipping_id = shipping_id;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public String getShipping_desc() {
            return shipping_desc;
        }

        public void setShipping_desc(String shipping_desc) {
            this.shipping_desc = shipping_desc;
        }

        public String getInsure() {
            return insure;
        }

        public void setInsure(String insure) {
            this.insure = insure;
        }

        public String getSupport_cod() {
            return support_cod;
        }

        public void setSupport_cod(String support_cod) {
            this.support_cod = support_cod;
        }

        public String getConfigure() {
            return configure;
        }

        public void setConfigure(String configure) {
            this.configure = configure;
        }

        public String getSupport_pickup() {
            return support_pickup;
        }

        public void setSupport_pickup(String support_pickup) {
            this.support_pickup = support_pickup;
        }
    }

    public static class PaymentListBean {
        /**
         * pay_id : 1
         * pay_code : alipay
         * pay_name : 支付宝
         * pay_fee : 0
         * pay_desc : 支付宝支付
         * pay_config : a:4:{i:0;a:3:{s:4:"name";s:14:"alipay_account";s:4:"type";s:4:"text";s:5:"value";s:16:"413266736@qq.com";}i:1;a:3:{s:4:"name";s:10:"alipay_key";s:4:"type";s:4:"text";s:5:"value";s:32:"j011csm6v7nrotp4y4hkvv8bnwxm493h";}i:2;a:3:{s:4:"name";s:14:"alipay_partner";s:4:"type";s:4:"text";s:5:"value";s:16:"2088531703603781";}i:3;a:3:{s:4:"name";s:17:"alipay_pay_method";s:4:"type";s:6:"select";s:5:"value";s:1:"2";}}
         * is_cod : 0
         * format_pay_fee :
         */

        private String pay_id;
        private String pay_code;
        private String pay_name;
        private String pay_fee;
        private String pay_desc;
        private String pay_config;
        private String is_cod;
        private String format_pay_fee;

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getPay_desc() {
            return pay_desc;
        }

        public void setPay_desc(String pay_desc) {
            this.pay_desc = pay_desc;
        }

        public String getPay_config() {
            return pay_config;
        }

        public void setPay_config(String pay_config) {
            this.pay_config = pay_config;
        }

        public String getIs_cod() {
            return is_cod;
        }

        public void setIs_cod(String is_cod) {
            this.is_cod = is_cod;
        }

        public String getFormat_pay_fee() {
            return format_pay_fee;
        }

        public void setFormat_pay_fee(String format_pay_fee) {
            this.format_pay_fee = format_pay_fee;
        }
    }
}
