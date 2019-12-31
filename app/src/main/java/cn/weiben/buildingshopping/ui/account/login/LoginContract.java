package cn.weiben.buildingshopping.ui.account.login;

import cn.weiben.buildingshopping.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView{
        void loginSuccess();

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void login(String name, String password);
    }
}
