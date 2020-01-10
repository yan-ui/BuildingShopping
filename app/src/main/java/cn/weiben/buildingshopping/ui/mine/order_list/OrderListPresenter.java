package cn.weiben.buildingshopping.ui.mine.order_list;

import android.util.Log;

import com.google.gson.Gson;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.test.list.ListContract;


/**
 * 描述
 */
public class OrderListPresenter extends BasePresenter<OrderListContract.View> implements OrderListContract.Presenter {

    @Override
    public void getOrderList(int page, int composite_status) {
        RetrofitHelper.getInstance().getServer()
                .getOrderList(page,composite_status)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result ->{
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() == 1){
                        mView.setData(page,result.getData().getOrder_list());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    mView.setData(page,null);
                });
    }

    @Override
    public void cancelOrder(String order_id) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .cancelOrder(order_id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result ->{
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() == 1){
                        mView.cancelOrderSuccess();
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
