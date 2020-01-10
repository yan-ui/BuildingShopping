package cn.weiben.buildingshopping.ui.home.goods_detail.test;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.BuyGoods;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.GoodsDetail;
import cn.weiben.buildingshopping.model.HomeBean;

public interface GoodsDetailContract {
    interface View extends BaseContract.BaseView{
        void setGoodsDetail(GoodsDetail bean);

    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getGoodsDetail(String id);
        void collectGoods(String id);
        void addShopCart(BuyGoods goods);
    }
}
