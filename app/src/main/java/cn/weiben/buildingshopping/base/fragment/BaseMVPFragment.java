package cn.weiben.buildingshopping.base.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import cn.weiben.buildingshopping.base.BaseContract;


/**
 * 懒加载Fragment基类
 *
 * @param <T>
 */
public abstract class BaseMVPFragment<T extends BaseContract.BasePresenter> extends BaseFragment {
    private static final String TAG = "BaseMVPFragment";

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    /**
     * 运行在onCreateView之后
     * 加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        attachView();
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDetach() {
        detachView();
        super.onDetach();
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
    protected abstract T initPresenter();


}
