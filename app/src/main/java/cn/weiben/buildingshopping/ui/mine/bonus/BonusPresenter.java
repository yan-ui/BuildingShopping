package cn.weiben.buildingshopping.ui.mine.bonus;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.MineContract;

/**
 * 描述
 */
public class BonusPresenter extends BasePresenter<BonusContract.View> implements BonusContract.Presenter {


    @Override
    public void getBonus(int page) {
        RetrofitHelper.getInstance().getServer()
                .getBonusList(page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.setBonusList(page, result.getData().getBouns());
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

    @Override
    public void addBonus(String bonus_sn) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .addBonus(bonus_sn)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result -> {
                    mView.showSuccess(result.getErrmsg());
                    if (result.getCode() == 1) {
                        mView.addBonusSuccess();
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }
}
