package cn.weiben.buildingshopping.ui.home;

import android.util.Log;

import com.google.gson.Gson;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;

/**
 * 描述
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public void getHomePage() {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .getHomePage()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setHomePage(result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                });
    }

    @Override
    public void getHomeGoodsList(int page,int last, int amount) {
        RetrofitHelper.getInstance().getServer()
                .getHomeGoodsList(last, amount)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setHomeGoodsList(page,result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                });
    }
}
