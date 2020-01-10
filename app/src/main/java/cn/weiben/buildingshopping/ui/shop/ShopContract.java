package cn.weiben.buildingshopping.ui.shop;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.ShopBean;

/**
 * 主页配置约定
 */
public interface ShopContract {
    interface View extends BaseContract.BaseView{
        void setShopBean(int page, ShopBean bean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getShopBean(int page);
    }
}

