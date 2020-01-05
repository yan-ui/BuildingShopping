package cn.weiben.buildingshopping.model;

import java.util.List;

public class UserBean {


    /**
     * order_count : {"all":"1","finished":"0","await_ship":"0","await_pay":"1","await_receipt":"0","unconfirmed":"1"}
     * collect_count : 0
     * comment_count : 0
     * headimgurl :
     * recomm :
     * info : {"username":"","headimg":"https://shop.shenghuopu.cn/","shop_name":"建材一站商城","integral":"100积分","is_validate":0,"credit_line":"0.00","formated_credit_line":"0.00","mobile_phone":"18133676739","email":"","status":"0","is_validated":"0","validated":"1","last_time":"2020-01-05 15:31:51","surplus":"0.00","bonus":"共计 0 个,价值 0.00","order_count":"1","shipped_order":[]}
     * user_notice : 用户中心公告！
     * prompt : []
     */

    private OrderCountBean order_count;
    private String collect_count;
    private String comment_count;
    private String headimgurl;
    private String recomm;
    private InfoBean info;
    private String user_notice;
    private List prompt;

    public OrderCountBean getOrder_count() {
        return order_count;
    }

    public void setOrder_count(OrderCountBean order_count) {
        this.order_count = order_count;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getRecomm() {
        return recomm;
    }

    public void setRecomm(String recomm) {
        this.recomm = recomm;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getUser_notice() {
        return user_notice;
    }

    public void setUser_notice(String user_notice) {
        this.user_notice = user_notice;
    }

    public List getPrompt() {
        return prompt;
    }

    public void setPrompt(List prompt) {
        this.prompt = prompt;
    }

    public static class OrderCountBean {
        /**
         * all : 1
         * finished : 0
         * await_ship : 0
         * await_pay : 1
         * await_receipt : 0
         * unconfirmed : 1
         */

        private int all;
        private int finished;
        private int await_ship;
        private int await_pay;
        private int await_receipt;
        private int unconfirmed;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getFinished() {
            return finished;
        }

        public void setFinished(int finished) {
            this.finished = finished;
        }

        public int getAwait_ship() {
            return await_ship;
        }

        public void setAwait_ship(int await_ship) {
            this.await_ship = await_ship;
        }

        public int getAwait_pay() {
            return await_pay;
        }

        public void setAwait_pay(int await_pay) {
            this.await_pay = await_pay;
        }

        public int getAwait_receipt() {
            return await_receipt;
        }

        public void setAwait_receipt(int await_receipt) {
            this.await_receipt = await_receipt;
        }

        public int getUnconfirmed() {
            return unconfirmed;
        }

        public void setUnconfirmed(int unconfirmed) {
            this.unconfirmed = unconfirmed;
        }
    }

    public static class InfoBean {
        /**
         * username :
         * headimg : https://shop.shenghuopu.cn/
         * shop_name : 建材一站商城
         * integral : 100积分
         * is_validate : 0
         * credit_line : 0.00
         * formated_credit_line : 0.00
         * mobile_phone : 18133676739
         * email :
         * status : 0
         * is_validated : 0
         * validated : 1
         * last_time : 2020-01-05 15:31:51
         * surplus : 0.00
         * bonus : 共计 0 个,价值 0.00
         * order_count : 1
         * shipped_order : []
         */

        private String username;
        private String headimg;
        private String shop_name;
        private String integral;
        private int is_validate;
        private String credit_line;
        private String formated_credit_line;
        private String mobile_phone;
        private String email;
        private String status;
        private String is_validated;
        private String validated;
        private String last_time;
        private String surplus;
        private String bonus;
        private String order_count;
        private List<?> shipped_order;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public int getIs_validate() {
            return is_validate;
        }

        public void setIs_validate(int is_validate) {
            this.is_validate = is_validate;
        }

        public String getCredit_line() {
            return credit_line;
        }

        public void setCredit_line(String credit_line) {
            this.credit_line = credit_line;
        }

        public String getFormated_credit_line() {
            return formated_credit_line;
        }

        public void setFormated_credit_line(String formated_credit_line) {
            this.formated_credit_line = formated_credit_line;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIs_validated() {
            return is_validated;
        }

        public void setIs_validated(String is_validated) {
            this.is_validated = is_validated;
        }

        public String getValidated() {
            return validated;
        }

        public void setValidated(String validated) {
            this.validated = validated;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public List<?> getShipped_order() {
            return shipped_order;
        }

        public void setShipped_order(List<?> shipped_order) {
            this.shipped_order = shipped_order;
        }
    }
}
