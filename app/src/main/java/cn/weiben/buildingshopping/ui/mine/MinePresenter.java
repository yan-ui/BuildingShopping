package cn.weiben.buildingshopping.ui.mine;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.account.login.LoginContract;

/**
 * 描述
 */
public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {

    @Override
    public void getUser() {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .getUser()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {

                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
