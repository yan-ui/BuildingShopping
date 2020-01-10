package cn.weiben.buildingshopping.ui.cart;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.CartBean;
import cn.weiben.buildingshopping.model.UserBean;

public interface CartContract {
    interface View extends BaseContract.BaseView{
        void setCartList(CartBean bean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getCartList();
        void deleteGoods(String ids);
    }
}
