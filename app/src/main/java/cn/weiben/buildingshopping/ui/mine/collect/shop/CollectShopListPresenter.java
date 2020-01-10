package cn.weiben.buildingshopping.ui.mine.collect.shop;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.collect.goods.CollectGoodsListContract;


/**
 * 描述
 */
public class CollectShopListPresenter extends BasePresenter<CollectShopListContract.View> implements CollectShopListContract.Presenter {
    @Override
    public void getCollectShopList(int page) {
        RetrofitHelper.getInstance().getServer()
                .getCollectShopList(page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result ->{
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() == 1){
                        mView.setData(page,result.getData());
                    }
                }, throwable -> {
                    mView.showFailed(throwable.getMessage());
                    mView.setData(page,null);
                });
    }

    @Override
    public void delete(int position, String rec_id) {
        mView.showLoading();
        RetrofitHelper.getInstance().getServer()
                .deleteCollcetShop(rec_id)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindUntilEvent())
                .subscribe(result ->{
                    mView.showSuccess(result.getErrmsg());
                    if(result.getCode() == 1){
                        mView.deleteSuccess(position);
                    }
                }, throwable -> {
                    mView.showSuccess(throwable.getMessage());
                });
    }

}
