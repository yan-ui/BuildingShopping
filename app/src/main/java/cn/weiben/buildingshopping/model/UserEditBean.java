package cn.weiben.buildingshopping.model;

import java.util.List;

public class UserEditBean {


    /**
     * headimgurl : https://shop.shenghuopu.cn/mobile/headimg/157770461996h.png
     * profile : {"rank_name":"普通会员","discount":"100%","email":"1490438682@qq.com","headimg":"https://shop.shenghuopu.cn/mobile/headimg/157770461996h.png","user_name":"","password":"13c06ad9bb5a97af01ef2f30a1866c44","rank_points":"100","pay_points":"100积分","user_money":"0.00","sex":"1","birthday":"1997-04-15","question":"","bonus":[],"qq":"","msn":"","office_phone":"","home_phone":"","mobile_phone":"15655196840","passwd_question":null,"passwd_answer":null,"real_name":"","card":"","face_card":"","back_card":"","country":"0","province":"0","city":"0","district":"0","address":"","validated":"1","is_validated":"0","status":"0","is_surplus_open":"0","surplus_password":""}
     */

    private String headimgurl;
    private ProfileBean profile;

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public static class ProfileBean {
        /**
         * rank_name : 普通会员
         * discount : 100%
         * email : 1490438682@qq.com
         * headimg : https://shop.shenghuopu.cn/mobile/headimg/157770461996h.png
         * user_name :
         * password : 13c06ad9bb5a97af01ef2f30a1866c44
         * rank_points : 100
         * pay_points : 100积分
         * user_money : 0.00
         * sex : 1
         * birthday : 1997-04-15
         * question :
         * bonus : []
         * qq :
         * msn :
         * office_phone :
         * home_phone :
         * mobile_phone : 15655196840
         * passwd_question : null
         * passwd_answer : null
         * real_name :
         * card :
         * face_card :
         * back_card :
         * country : 0
         * province : 0
         * city : 0
         * district : 0
         * address :
         * validated : 1
         * is_validated : 0
         * status : 0
         * is_surplus_open : 0
         * surplus_password :
         */

        private String rank_name;
        private String discount;
        private String email;
        private String headimg;
        private String user_name;
        private String password;
        private String rank_points;
        private String pay_points;
        private String user_money;
        private String sex;
        private String birthday;
        private String question;
        private String qq;
        private String msn;
        private String office_phone;
        private String home_phone;
        private String mobile_phone;
        private Object passwd_question;
        private Object passwd_answer;
        private String real_name;
        private String card;
        private String face_card;
        private String back_card;
        private String country;
        private String province;
        private String city;
        private String district;
        private String address;
        private String validated;
        private String is_validated;
        private String status;
        private String is_surplus_open;
        private String surplus_password;
        private List bonus;

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRank_points() {
            return rank_points;
        }

        public void setRank_points(String rank_points) {
            this.rank_points = rank_points;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public String getHome_phone() {
            return home_phone;
        }

        public void setHome_phone(String home_phone) {
            this.home_phone = home_phone;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public Object getPasswd_question() {
            return passwd_question;
        }

        public void setPasswd_question(Object passwd_question) {
            this.passwd_question = passwd_question;
        }

        public Object getPasswd_answer() {
            return passwd_answer;
        }

        public void setPasswd_answer(Object passwd_answer) {
            this.passwd_answer = passwd_answer;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getFace_card() {
            return face_card;
        }

        public void setFace_card(String face_card) {
            this.face_card = face_card;
        }

        public String getBack_card() {
            return back_card;
        }

        public void setBack_card(String back_card) {
            this.back_card = back_card;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getValidated() {
            return validated;
        }

        public void setValidated(String validated) {
            this.validated = validated;
        }

        public String getIs_validated() {
            return is_validated;
        }

        public void setIs_validated(String is_validated) {
            this.is_validated = is_validated;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIs_surplus_open() {
            return is_surplus_open;
        }

        public void setIs_surplus_open(String is_surplus_open) {
            this.is_surplus_open = is_surplus_open;
        }

        public String getSurplus_password() {
            return surplus_password;
        }

        public void setSurplus_password(String surplus_password) {
            this.surplus_password = surplus_password;
        }

        public List getBonus() {
            return bonus;
        }

        public void setBonus(List bonus) {
            this.bonus = bonus;
        }
    }
}
