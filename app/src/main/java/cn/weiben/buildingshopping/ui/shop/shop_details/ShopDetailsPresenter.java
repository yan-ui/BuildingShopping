package cn.weiben.buildingshopping.ui.shop.shop_details;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.shop.ShopContract;

/**
 * 描述
 */
public class ShopDetailsPresenter extends BasePresenter<ShopDetailsContract.View> implements ShopDetailsContract.Presenter {


    @Override
    public void getShopDetails(String id) {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getShopDetails(id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                      mView.setShopDetails(result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                });
    }

    @Override
    public void collectShop(String suppId) {
        RetrofitHelper.getInstance().getServer()
                .collectShop(suppId)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

}
