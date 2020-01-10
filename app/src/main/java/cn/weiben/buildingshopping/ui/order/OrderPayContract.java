package cn.weiben.buildingshopping.ui.order;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.AddressModel;
import cn.weiben.buildingshopping.model.CartPayBean;


public interface OrderPayContract {
    interface View extends BaseContract.BaseView {
        void setCartPayData(CartPayBean bean);

        void setAddressList(List<AddressModel> datas);

        void payOrderSuccess();

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddressList();

        void payCartGoods(String isCart,String goods);

        void payOrder(String pay_ship, String best_time, int how_oos, String postscript,
                      String integral, int payment, String addreess_id);
    }
}
