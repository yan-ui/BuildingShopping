package cn.weiben.buildingshopping.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.billy.android.loading.Gloading;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.components.support.RxFragment;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.base.activity.BaseActivity;
import cn.weiben.buildingshopping.base.interfaces.LazyFragmentControl;
import cn.weiben.buildingshopping.base.loading.SpecialAdapter;


/**
 * 懒加载Fragment基类
 */
public abstract class BaseFragment extends RxFragment implements LazyFragmentControl, BaseContract.BaseView {
    private static final String TAG = "BaseFragment";

    protected BaseActivity mActivity;//activity的上下文对象
    protected Bundle mBundle;

    private static final long DEFAULT_TIME_INTERVAL = 5 * 1000;//默认间隔时间30秒

    private boolean isAlive = false;
    private boolean isPrepared;
    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    protected boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    private long mLastVisibleTime = System.currentTimeMillis();//上一次显示的时间
    private long mTimeInterval = DEFAULT_TIME_INTERVAL;

    //加载视图
    private Gloading.Holder mHolder;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
    }

    /**
     * 绑定activity
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }


    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
    }

    /**
     * 运行在onCreate之后
     * 生成view视图
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isAlive = true;
        View mContentView = inflater.inflate(getFragmentLayoutID(), container, false);
        mHolder = Gloading.from(new SpecialAdapter()).wrap(mContentView).withRetry(new Runnable() {
            @Override
            public void run() {
                onRetry();
            }
        });
        return mHolder.getWrapper();
    }

    /**
     * 运行在onCreateView之后
     * 加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initPrepare();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }


    /**
     * 当fragment结合viewpager使用的时候 这个方法会调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                checkLastTime();
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                //界面不显示 设置上次显示时间
                mLastVisibleTime = System.currentTimeMillis();
                onUserInvisible();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHolder = null;
        mActivity = null;
        mBundle = null;
    }

    @Override
    public void onDetach() {
        isAlive = false;
        super.onDetach();
    }


    @Override
    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
            lazyData();
        } else {
            isPrepared = true;
        }
    }


    //运行线程<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 在UI线程中运行，建议用这个方法代替runOnUiThread
     *
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (isAlive == false) {
            LogUtils.w("runUiThread  isAlive() == false >> return;");
            return;
        }
        mActivity.runUiThread(action);
    }

    /**
     * 运行线程
     *
     * @param name
     * @param runnable
     * @return
     */
    public final Handler runThread(String name, Runnable runnable) {
        if (isAlive == false) {
            LogUtils.w("runThread  isAlive() == false >> return null;");
            return null;
        }
        return mActivity.runThread(name + hashCode(), runnable);//name, runnable);同一Activity出现多个同名Fragment可能会出错
    }
    //运行线程>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 设置Fragment的布局ID
     *
     * @return
     */
    protected abstract int getFragmentLayoutID();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 类似Activity的OnBackgress
     * fragment进行回退
     */
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void showLoading() {
        mHolder.showLoading();
    }

    @Override
    public void showPageLoading() {
        mHolder.showLoadingStatus(SpecialAdapter.STATUS_PAGE_LOADING);
    }

    @Override
    public void showSuccess(String message) {
        mHolder.showLoadSuccess();
        if (!StringUtils.isEmpty(message) && !message.equals("success")) {
            ToastUtils.showShort(message);
        }
    }

    @Override
    public void showFailed(String message) {
        mHolder.showLoadFailed();
        if (!StringUtils.isEmpty(message)) {
            ToastUtils.showShort(message);
        }
    }

    @Override
    public void showNoNet() {
        mHolder.showLoadFailed();
    }

    @Override
    public void showNoData() {
        mHolder.showEmpty();
    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent() {
        return this.bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void checkLastTime() {
        if (System.currentTimeMillis() - mLastVisibleTime > mTimeInterval) {
            onAutoRefresh();
        }
    }

    @Override
    public void onFirstUserVisible() {
        Log.i(TAG, "onFirstUserVisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onUserVisible() {
        Log.i(TAG, "onUserVisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onFirstUserInvisible() {
        Log.i(TAG, "onFirstUserInvisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onUserInvisible() {
        Log.i(TAG, "onUserInvisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onAutoRefresh() {
        Log.i(TAG, "onAutoRefresh:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void setTimeInterval(long mTimeInterval) {
        this.mTimeInterval = mTimeInterval;
    }


    protected void closeLoading() {
        if (mHolder != null) {
            mHolder.showLoadSuccess();
        }
    }
}
