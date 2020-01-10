package cn.weiben.buildingshopping.ui.order;

import java.util.List;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.api.model.BaseResult;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.model.AddressModel;
import cn.weiben.buildingshopping.model.CartPayBean;
import io.reactivex.functions.Consumer;


public class OrderPayPresenter extends BasePresenter<OrderPayContract.View> implements OrderPayContract.Presenter {

    @Override
    public void getAddressList() {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .getAddressList()
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe((Consumer<BaseResult<List<AddressModel>>>) result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setAddressList(result.getData());
                    }
                }, (Consumer<Throwable>) throwable -> {
                    mView.setAddressList(null);
                    mView.showSuccess("");
                });
    }

    @Override
    public void payCartGoods(String isCart,String goods) {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .payCartGoods(isCart,goods)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe((Consumer<BaseResult<CartPayBean>>) result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setCartPayData(result.getData());
                    }
                }, (Consumer<Throwable>) throwable -> {
                    mView.showSuccess(throwable.getMessage());
                    mView.setCartPayData(null);
                });
    }

    @Override
    public void payOrder(String pay_ship, String best_time, int how_oos, String postscript, String integral, int payment, String addreess_id) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .payOrder(null,null,pay_ship,best_time,how_oos,postscript,integral,payment,addreess_id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe((Consumer<BaseResult>) result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.payOrderSuccess();
                    }
                }, (Consumer<Throwable>) throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
