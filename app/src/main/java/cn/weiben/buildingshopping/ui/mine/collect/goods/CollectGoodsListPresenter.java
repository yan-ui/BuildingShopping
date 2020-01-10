package cn.weiben.buildingshopping.ui.mine.collect.goods;

import cn.weiben.buildingshopping.api.RetrofitHelper;
import cn.weiben.buildingshopping.api.RxSchedulers;
import cn.weiben.buildingshopping.base.BasePresenter;
import cn.weiben.buildingshopping.ui.mine.order_list.OrderListContract;


/**
 * 描述
 */
public class CollectGoodsListPresenter extends BasePresenter<CollectGoodsListContract.View> implements CollectGoodsListContract.Presenter {

    @Override
    public void getCollectGoodsList(int page) {
        RetrofitHelper.getInstance().getServer()
                .getCollectGoodsList(page)
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
                .deleteCollcetGoods(rec_id)
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
