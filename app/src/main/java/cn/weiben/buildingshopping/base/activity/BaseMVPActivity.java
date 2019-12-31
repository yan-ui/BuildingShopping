package cn.weiben.buildingshopping.base.activity;


import cn.weiben.buildingshopping.base.BaseContract;

/**
 * 类描述
 */
public abstract class BaseMVPActivity<P extends BaseContract.BasePresenter> extends BaseActivity{

    protected P mPresenter;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        mPresenter = initPresenter();
        attachView();
    }

    /**
     * 挂载view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 卸载view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
    }


}
