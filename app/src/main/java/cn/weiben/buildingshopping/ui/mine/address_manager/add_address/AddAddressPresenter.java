package cn.weiben.buildingshopping.ui.mine.address_manager.add_address;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;

/**
 * 描述
 */
public class AddAddressPresenter extends BasePresenter<AddAddressContract.View> implements AddAddressContract.Presenter {

    @Override
    public void addAddressArea(String consignee, String email, String province, String city, String district, String address, String mobile, String zipcode, String address_id) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .addAddressArea(consignee,email,"1",province,city,district,"0",address,mobile,zipcode,address_id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.addSuccess();
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void getAddressDetails(String address_id) {
        mView.showPageLoading();
        RetrofitHelper.getInstance().getServer()
                .getAddressDetails(address_id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setAddressDetails(result.getData());
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
