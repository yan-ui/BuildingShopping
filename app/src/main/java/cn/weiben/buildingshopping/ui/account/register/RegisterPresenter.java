package cn.weiben.buildingshopping.ui.account.register;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.account.login.LoginContract;

/**
 * 描述
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void register(String name, String password, String captcha, String code) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .register("mobile", name, password, captcha, code)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.registerSuccess();
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }


    @Override
    public void getSms(String mobile, String captcha) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .getSms(mobile, captcha)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.getCaptchaSuccess();
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
