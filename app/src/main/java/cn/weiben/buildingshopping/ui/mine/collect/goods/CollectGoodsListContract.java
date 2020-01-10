package cn.weiben.buildingshopping.ui.mine.collect.goods;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.CollectGoodsBean;
import cn.weiben.buildingshopping.model.OrderResultBean;


public interface CollectGoodsListContract {
    interface View extends BaseContract.BaseView{
        void setData(int page, List<CollectGoodsBean> data);
        void deleteSuccess(int position);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
       void getCollectGoodsList(int page);
       void delete(int position,String rec_id);
    }

}
