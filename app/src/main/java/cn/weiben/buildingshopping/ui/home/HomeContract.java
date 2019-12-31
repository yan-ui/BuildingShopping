package cn.weiben.buildingshopping.ui.home;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.HomeBean;

/**
 * 主页配置约定
 */

public interface HomeContract {
    interface View extends BaseContract.BaseView{
        void setHomePage(HomeBean bean);
        void setHomeGoodsList(int page,List<GoodsBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getHomePage();
        void getHomeGoodsList(int page,int last,int amount);
    }
}
