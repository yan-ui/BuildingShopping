package cn.weiben.buildingshopping.model;

public class MoneyManagerBean {


    /**
     * user_info : {"user_id":"1711","email":"1490438682@qq.com","user_name":"caicaibai","user_money":"0.00","pay_points":"100","reg_time":"2019-11-06","username":"caicaibai","user_points":"100积分","user_bonus":"20.00","bonus_count":"1"}
     */

    private UserInfoBean user_info;

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean {
        /**
         * user_id : 1711
         * email : 1490438682@qq.com
         * user_name : caicaibai
         * user_money : 0.00
         * pay_points : 100
         * reg_time : 2019-11-06
         * username : caicaibai
         * user_points : 100积分
         * user_bonus : 20.00
         * bonus_count : 1
         */

        private String user_id;
        private String email;
        private String user_name;
        private String user_money;
        private String pay_points;
        private String reg_time;
        private String username;
        private String user_points;
        private String user_bonus;
        private String bonus_count;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUser_points() {
            return user_points;
        }

        public void setUser_points(String user_points) {
            this.user_points = user_points;
        }

        public String getUser_bonus() {
            return user_bonus;
        }

        public void setUser_bonus(String user_bonus) {
            this.user_bonus = user_bonus;
        }

        public String getBonus_count() {
            return bonus_count;
        }

        public void setBonus_count(String bonus_count) {
            this.bonus_count = bonus_count;
        }
    }
}
