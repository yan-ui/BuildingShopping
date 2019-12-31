package cn.weiben.buildingshopping.ui.shop;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;

/**
 * 描述
 */
public class ShopPresenter extends BasePresenter<ShopContract.View> implements ShopContract.Presenter {

    @Override
    public void getShopBean(int page) {
        RetrofitHelper.getInstance().getServer()
                .getShopBean(page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setShopBean(page, result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                });
    }
}
