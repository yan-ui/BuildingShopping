package cn.weiben.buildingshopping.ui.test.list;

import android.util.Log;

import com.google.gson.Gson;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;


/**
 * 描述
 */
public class ListPresenter extends BasePresenter<ListContract.View> implements ListContract.Presenter {

    private static final String TAG = "ListPresenter";
    @Override
    public void getList(int page) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .index("推荐",page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result ->{
                    Log.e(TAG, "getList:  -----------------------------          "+new Gson().toJson(result));
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() == 1){
                        mView.setData(page,result.getData().getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    mView.setData(page,null);
                });
    }

    @Override
    public void getListNoLoading(int page) {
        RetrofitHelper.getInstance().getServer()
                .index("推荐",page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result ->{
                    Log.e(TAG, "getList:  ----------------12-------------          "+result.getCode() );
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() == 1){
                        mView.setData(page,result.getData().getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    mView.setData(page,null);
                });
    }
}
