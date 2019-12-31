package cn.weiben.buildingshopping.ui.mine;

import cn.weiben.buildingshopping.base.BaseContract;

public interface MineContract {
    interface View extends BaseContract.BaseView{


    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getUser();
    }
}
