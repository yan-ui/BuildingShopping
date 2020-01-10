package cn.weiben.buildingshopping.ui.home.goods_detail.test;

import com.google.gson.Gson;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.model.BuyGoods;

/**
 * 描述
 */
public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter {


    @Override
    public void getGoodsDetail(String id) {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getGoodsDetail(id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setGoodsDetail(result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    throwable.printStackTrace();
                });
    }

    @Override
    public void collectGoods(String id) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .collectGoods(id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void addShopCart(BuyGoods goods) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .addShopCart(new Gson().toJson(goods))
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
