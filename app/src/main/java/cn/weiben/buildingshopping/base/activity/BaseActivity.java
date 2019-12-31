package cn.weiben.buildingshopping.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.billy.android.loading.Gloading;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.base.TitlebarHelper;
import cn.weiben.buildingshopping.base.loading.SpecialAdapter;
import cn.weiben.buildingshopping.manager.ThreadManager;

import static cn.weiben.buildingshopping.base.loading.SpecialAdapter.STATUS_PAGE_LOADING;


/**
 * 类描述
 */
public abstract class BaseActivity extends RxAppCompatActivity implements BaseContract.BaseView {
    private Handler baseHandler = new Handler();

    private CommonTitleBar mBaseTitleBar;

    private TitlebarHelper titlebarHelper;

    protected FragmentManager fragmentManager;

    protected Activity mContext;
    protected View mContentView;

    private boolean isAlive = false;

    //加载Loading
    private Gloading.Holder mHolder;

    private void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.from(new SpecialAdapter()).wrap(mContentView).withRetry(new Runnable() {
                @Override
                public void run() {
                    onRetry();
                }
            });
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_common_layout);
        isAlive = true;
        threadNameList = new ArrayList<>();

        if (isShowTitleBar()) {
            ViewStub mBaseTitleBarStub = findViewById(R.id.mBaseTitleBarStub);
            mBaseTitleBar = (CommonTitleBar) mBaseTitleBarStub.inflate();
            mBaseTitleBar.setBackgroundResource(R.drawable.shape_gradient_common_titlebar);
            titlebarHelper = new TitlebarHelper(mBaseTitleBar);
            titlebarHelper.setNavigationImage();
            titlebarHelper.setNavigationOnClickListener(v -> {
                onBackPressed();
            });
        } else {
            if (isUseStatusBarContent()) {
                StatusBarUtils.transparentStatusBar(this.getWindow());
            }
        }

        ViewStub contentStub = findViewById(R.id.base_content_stub);
        contentStub.setLayoutResource(getActivityLayoutID());
        mContentView = contentStub.inflate();

        initBaseView();

        initView(savedInstanceState);
    }


    /**
     * 子类重写此方法控制titlebar 是否显示
     *
     * @return 是否显示标题栏
     */
    protected boolean isShowTitleBar() {
        return true;
    }

    /**
     * 子类重写此方法控制是否占用状态栏空间
     *
     * @return 是否占用状态栏空间(透明状态栏)
     */
    protected boolean isUseStatusBarContent() {
        return true;
    }

    /**
     * 切换状态栏颜色
     *
     * @param isLigth true:状态栏字体颜色为白色，反之为黑色
     */
    protected void toggleStatusBarMode(boolean isLigth) {
        if (isLigth) {
            StatusBarUtils.setLightMode(this.getWindow());
        } else {
            StatusBarUtils.setDarkMode(this.getWindow());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThreadManager.getInstance().destroyThread(threadNameList);
        isAlive = false;
        fragmentManager = null;
        threadNameList = null;
        mContentView = null;
        mBaseTitleBar = null;
        mHolder = null;
    }

    protected void initBaseView() {
        fragmentManager = getSupportFragmentManager();
        mContext = this;
    }


    /**
     * 设置Activity的布局ID
     *
     * @return
     */
    protected abstract int getActivityLayoutID();

    /**
     * 初始化View
     *
     * @param savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);


    @Override
    public void showLoading() {
        runUiThread(() -> {
                    initLoadingStatusViewIfNeed();
                    mHolder.showLoading();
                }
        );
    }

    @Override
    public void showPageLoading() {
        runUiThread(() -> {
            initLoadingStatusViewIfNeed();
            mHolder.showLoadingStatus(STATUS_PAGE_LOADING);
        });
    }


    @Override
    public void showSuccess(String message) {
        runUiThread(() -> {
            initLoadingStatusViewIfNeed();
            mHolder.showLoadSuccess();
            if (!StringUtils.isEmpty(message) && !message.equals("success")) {
                ToastUtils.showShort(message);
            }
        });
    }

    /**
     * 显示默认布局 关闭loading
     */
    public void closeLoading() {
        mHolder.showLoadSuccess();
    }

    @Override
    public void showFailed(String message) {
        runUiThread(() -> {
            initLoadingStatusViewIfNeed();
            mHolder.showLoadFailed();
            if (!StringUtils.isEmpty(message)) {
                ToastUtils.showShort(message);
            }
        });

    }

    @Override
    public void showNoNet() {
        runUiThread(() -> {
            initLoadingStatusViewIfNeed();
            mHolder.showLoadFailed();
        });

    }

    @Override
    public void showNoData() {
        runUiThread(() -> {
            initLoadingStatusViewIfNeed();
            mHolder.showEmpty();
        });

    }


    @Override
    public void onRetry() {

    }


    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent() {
        return this.bindUntilEvent(ActivityEvent.DESTROY);
    }


    /**
     * 显示软件盘
     */
    public void showSoftInput(EditText view) {
        if (view != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.showSoftInput(view, 0);
            }
        }
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //运行线程 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 在UI线程中运行，建议用这个方法代替runOnUiThread
     *
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (!isAlive) {
            LogUtils.w("runUiThread  isAlive() == false >> return;");
            return;
        }
        runOnUiThread(action);
    }

    /**
     * 线程名列表
     */
    protected List<String> threadNameList;

    /**
     * 运行线程
     *
     * @param name
     * @param runnable
     * @return
     */
    public final Handler runThread(String name, Runnable runnable) {
        if (!isAlive) {
            LogUtils.w("runThread  isAlive() == false >> return null;");
            return null;
        }
        Handler handler = ThreadManager.getInstance().runThread(name, runnable);
        if (handler == null) {
            LogUtils.e("runThread handler == null >> return null;");
            return null;
        }

        if (!threadNameList.contains(name)) {
            threadNameList.add(name);
        }
        return handler;
    }

    //运行线程 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //===========================================标题栏设置方法区域===================================================

    /**
     * 获取标题栏对象
     */
    public CommonTitleBar getCommonTitleBar() {
        return mBaseTitleBar;
    }


    /**
     * 设置标题栏中间文字内容
     *
     * @param title
     */
    public void setTitle(String title) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setTitle(title);
    }


    /**
     * 设置标题栏 中间文字 与 中间副标题文字内容
     *
     * @param title
     * @param subTitle
     */
    public void setTitle(String title, String subTitle) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setTitle(title, subTitle);
    }


    public void setNavigationText(String leftText) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setNavigationText(leftText);
    }

    public void setNavigationText(String leftText, int leftDrawable) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setNavigationText(leftText, leftDrawable);
    }

    public void setNavigationImage() {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setNavigationImage();
    }

    public void setNavigationImage(int leftImageResource) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setNavigationImage(leftImageResource);
    }

    public void setNavigationCustom(int leftCustomViewRes) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setNavigationCustom(leftCustomViewRes);
    }

    public void setNavigationOnClickListener(CommonTitleBar.OnNavigationListener listener) {
        if (titlebarHelper == null) {
            return;
        }
        if (listener != null) {
            titlebarHelper.setNavigationOnClickListener(listener);
        }
    }


    public void setPositiveText(String rightText) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setPositiveText(rightText);
    }

    public void setPositiveImage(int rightImageResource) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setPositiveImage(rightImageResource);
    }

    public void setPositiveCustom(int rightCustomViewRes) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setPositiveCustom(rightCustomViewRes);
    }

    public void setPositiveOnClickListener(CommonTitleBar.OnPositiveListener listener) {
        if (titlebarHelper == null) {
            return;
        }
        titlebarHelper.setPositiveOnClickListener(listener);
    }


}

