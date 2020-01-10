package cn.weiben.buildingshopping.ui.mine.leave_message;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;

/**
 * 描述
 */
public class LeaveMessageListPresenter extends BasePresenter<LeaveMessageListContract.View> implements LeaveMessageListContract.Presenter {

    @Override
    public void getLeaveMessageList(int page) {
        RetrofitHelper.getInstance().getServer()
                .getMessage(page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setData(page,result.getData().getMessage_list());
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                    mView.setData(page,null);
                });
    }
}
