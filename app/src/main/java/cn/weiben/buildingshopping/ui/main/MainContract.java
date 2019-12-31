package cn.weiben.buildingshopping.ui.main;

import cn.weiben.buildingshopping.base.BaseContract;

/**
 * 主页配置约定
 */

public interface MainContract {
    interface View extends BaseContract.BaseView{
//        void loginSuccess(String msg);
//        void loginError(String msg);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void login(String name, String password);
    }
}
