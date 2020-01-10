package cn.weiben.buildingshopping.ui.mine.collect.shop;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.CollectGoodsBean;
import cn.weiben.buildingshopping.model.CollectShopBean;


public interface CollectShopListContract {
    interface View extends BaseContract.BaseView{
        void setData(int page, List<CollectShopBean> data);
        void deleteSuccess(int position);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
       void getCollectShopList(int page);
        void delete(int position,String rec_id);
    }

}
