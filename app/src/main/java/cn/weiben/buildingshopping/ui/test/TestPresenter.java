package cn.weiben.buildingshopping.ui.test;

import com.blankj.utilcode.util.LogUtils;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;


/**
 * 描述
 */
public class TestPresenter extends BasePresenter<TestContract.LoginView> implements TestContract.LoginPresenter {

    @Override
    public void login(final String name, final String password) {
        mView.showLoading();
//        RetrofitHelper.getInstance().getServer()
//                .test()
//                .compose(RxSchedulers.applySchedulers())
//                .compose(mView.bindUntilEvent())
//                .subscribe(result ->{
//                    mView.showSuccess("请求成功");
//                    LogUtils.e(result);
//                }, throwable -> {
//                    mView.showFailed("请求失败");
//                    LogUtils.e(throwable);
//                });
    }

}
