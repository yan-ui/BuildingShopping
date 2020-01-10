package cn.weiben.buildingshopping.ui.mine.address_manager;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.api.model.BaseResult;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.model.AddressModel;
import io.reactivex.functions.Consumer;


public class AddressManagerPresenter extends BasePresenter<AddressManagerContract.View> implements AddressManagerContract.Presenter {

    @Override
    public void getAddressList() {
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
                });
    }

    @Override
    public void deleteAddress(String id) {
        RetrofitHelper.getInstance().getServer()
                .deleteAddress(id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe((Consumer<BaseResult>) result -> {
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() ==1) {
                        mView.deleteSuccess();
                    }
                }, (Consumer<Throwable>) throwable -> {

                });
    }

    @Override
    public void setDefaultAddress(String id) {
        RetrofitHelper.getInstance().getServer()
                .setDefaultAddress(id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe((Consumer<BaseResult>) result -> {
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() ==1) {
                        mView.setDefaultSuccess();
                    }
                }, (Consumer<Throwable>) throwable -> {

                });
    }
}
