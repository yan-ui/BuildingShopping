package cn.weiben.buildingshopping.ui.category;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;

/**
 * 描述
 */
public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {

    @Override
    public void getCategoryList() {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .getCatalogList()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setCategoryList(result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                });
    }
}
