package cn.weiben.buildingshopping.ui.mine;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.UserBean;

public interface MineContract {
    interface View extends BaseContract.BaseView{
        void setUser(UserBean data);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getUser();
    }
}
