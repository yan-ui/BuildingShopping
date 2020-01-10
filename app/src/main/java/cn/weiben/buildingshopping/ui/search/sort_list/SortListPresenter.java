package cn.weiben.buildingshopping.ui.search.sort_list;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;

/**
 * 描述
 */
public class SortListPresenter extends BasePresenter<SortListContract.View> implements SortListContract.Presenter {

    @Override
    public void getSortList(String id,String sort,int page,int last,int amount,String order) {
        RetrofitHelper.getInstance().getServer()
                .searchGoods(id,sort,last,amount,order)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setSortList(page,result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    throwable.printStackTrace();
                });
    }
}