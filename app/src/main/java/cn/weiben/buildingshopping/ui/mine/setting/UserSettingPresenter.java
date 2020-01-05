package cn.weiben.buildingshopping.ui.mine.setting;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;

/**
 * 描述
 */
public class UserSettingPresenter extends BasePresenter<UserSettingContract.View> implements UserSettingContract.Presenter {

    @Override
    public void getUser() {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getUserProfile()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setUser(result.getData());
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void setUserProfile(String headimg, String user_name, String birthday, int sex, String email) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .setUserProfile(headimg,user_name,birthday,sex,email)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void setUserPassword(String oldPass, String newPass, String confirmPass) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
               .setUserPassword(oldPass, newPass, confirmPass)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
