package cn.weiben.buildingshopping.ui.cart;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.CartBean;

public interface CartContract {
    interface View extends BaseContract.BaseView {
        void setCartList(CartBean bean);

        void deleteSuccess(List<CartBean.GoodsListBeanX> datasTemp);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCartList();

        void deleteGoods(String ids, List<CartBean.GoodsListBeanX> datasTemp);

    }
}
