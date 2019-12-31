package cn.weiben.buildingshopping.ui.main;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;

import cn.weiben.buildingshopping.api.model.BasePageModel;
import cn.weiben.buildingshopping.api.model.BaseResult;
import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.api.model.Entity4;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.model.NewsBean;
import io.reactivex.Observable;
import io.reactivex.functions.Function4;

/**
 * 描述
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void login(final String name, final String password) {
        mView.showLoading();
//        Observable test = RetrofitHelper.getInstance().getServer().test();
//        Observable test1 = RetrofitHelper.getInstance().getServer().index("推荐", 1);
//        Observable test2 = RetrofitHelper.getInstance().getServer().index("推荐", 1);
//        Observable test3 = RetrofitHelper.getInstance().getServer().index("推荐", 1);
//        Observable.zip(test, test1, test2, test3, (Function4<Object, BaseResult<BasePageModel<NewsBean>>,
//                BaseResult<BasePageModel<NewsBean>>, BaseResult<BasePageModel<NewsBean>>,
//                Entity4<Object, BaseResult<BasePageModel<NewsBean>>, BaseResult<BasePageModel<NewsBean>>,
//                        BaseResult<BasePageModel<NewsBean>>>>)
//                (o, basePageModelBaseResult, basePageModelBaseResult2, basePageModelBaseResult3) ->
//                        new Entity4(o, basePageModelBaseResult, basePageModelBaseResult2, basePageModelBaseResult3))
//                .compose(RxSchedulers.applySchedulers())
//                .compose(mView.bindUntilEvent())
//                .subscribe(result -> {
//                    Entity4 res = (Entity4) result;
//                    mView.showSuccess("");
//                    LogUtils.e(new Gson().toJson(res.getValue1()));
//                }, throwable -> {
//                    mView.showFailed(((Throwable) throwable).getMessage());
//                });
    }
}
