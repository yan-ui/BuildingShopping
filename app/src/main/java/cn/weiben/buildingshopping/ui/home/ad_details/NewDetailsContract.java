package cn.weiben.buildingshopping.ui.home.ad_details;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.HomeBean;
import cn.weiben.buildingshopping.model.NewDetails;

/**
 * 主页配置约定
 */

public interface NewDetailsContract {
    interface View extends BaseContract.BaseView{
        void setNewDetails(NewDetails bean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getNewDetails(String id);
    }
}
