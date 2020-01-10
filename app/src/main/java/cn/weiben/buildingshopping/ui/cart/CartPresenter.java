package cn.weiben.buildingshopping.ui.cart;

import java.util.List;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.model.CartBean;

/**
 * 描述
 */
public class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.Presenter {

    @Override
    public void getCartList() {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .getCartList()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setCartList(result.getData());
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void deleteGoods(String ids, List<CartBean.GoodsListBeanX> datasTemp) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .deleteCartGoods(ids)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.deleteSuccess(datasTemp);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.showSuccess(throwable.getMessage());
                });
    }

}
