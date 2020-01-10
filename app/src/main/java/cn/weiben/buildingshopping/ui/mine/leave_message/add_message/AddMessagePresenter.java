package cn.weiben.buildingshopping.ui.mine.leave_message.add_message;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;

/**
 * 描述
 */
public class AddMessagePresenter extends BasePresenter<AddMessageContract.View> implements AddMessageContract.Presenter {

    @Override
    public void addMessage(int type, String msg_title, String msg_content) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .addMessage(type, msg_title, msg_content)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
