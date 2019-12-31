package cn.weiben.buildingshopping.ui.account.register;

import cn.weiben.buildingshopping.base.BaseContract;

public interface RegisterContract {
    interface View extends BaseContract.BaseView{
        void getCaptchaSuccess();
        void registerSuccess();
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void register(String name, String password,String captcha,String code);
        void getSms(String mobile,String captcha);
    }
}
