package cn.weiben.buildingshopping.ui.home.ad_details;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;

/**
 * 描述
 */
public class NewDetailsPresenter extends BasePresenter<NewDetailsContract.View> implements NewDetailsContract.Presenter {


    @Override
    public void getNewDetails(String id) {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getNewsDetail(id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setNewDetails(result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                });
    }
}
