package cn.weiben.buildingshopping.ui.home.goods_detail.test;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;

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
}
