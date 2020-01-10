package cn.weiben.buildingshopping.ui.mine.all_money;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;

/**
 * 描述
 */
public class AllMoneyPresenter extends BasePresenter<AllMoneyContract.View> implements AllMoneyContract.Presenter {

    @Override
    public void getUser() {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getAccountManage()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setUser(result.getData());
                    }else {
                        mView.setUser(null);
                    }
                }, throwable -> {
                    mView.setUser(null);
                    mView.showSuccess("");
                });
    }
}
