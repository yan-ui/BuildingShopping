package cn.weiben.buildingshopping.ui.account.login;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.manager.AccountHelper;
import cn.weiben.buildingshopping.ui.main.MainContract;

/**
 * 描述
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(final String name, final String password) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .login(name, password)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        AccountHelper.INSTANCE.saveToken(result.getData().getToken());
                        mView.loginSuccess();
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
