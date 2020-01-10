package cn.weiben.buildingshopping.ui.search.shop_list;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.ShopBean;

/**
 * 主页配置约定
 */
public interface ShopContract {
    interface View extends BaseContract.BaseView{
        void setShopBean(int page, List<ShopBean.SupplierListBean> data);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getShopBean(int page,String keywords);
        void collectShop(String suppId);
    }
}
