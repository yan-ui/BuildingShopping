package cn.weiben.buildingshopping.ui.test;

import cn.weiben.buildingshopping.base.BaseContract;

/**
 * 账号管理：登录注册配置约定
 */

public interface TestContract {
    interface LoginView extends BaseContract.BaseView{
        void loginSuccess(String msg);
        void loginError(String msg);
    }
    interface LoginPresenter extends BaseContract.BasePresenter<LoginView>{
        void login(String name, String password);
    }

    interface RegisterView extends BaseContract.BaseView{
        void registerSuccess(String msg);
        void registerError(String msg);
    }

    interface RegisterPresenter extends BaseContract.BasePresenter<RegisterView>{
        void register(String name,String password);
    }
}
