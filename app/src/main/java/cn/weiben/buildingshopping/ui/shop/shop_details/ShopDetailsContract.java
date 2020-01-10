package cn.weiben.buildingshopping.ui.shop.shop_details;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.ShopBean;
import cn.weiben.buildingshopping.model.ShopDetailsBean;

/**
 * 主页配置约定
 */
public interface ShopDetailsContract {
    interface View extends BaseContract.BaseView{

        void setShopDetails(ShopDetailsBean data);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getShopDetails(String id);
        void collectShop(String suppId);
    }
}

