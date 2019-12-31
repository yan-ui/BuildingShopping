package cn.weiben.buildingshopping;

import android.content.Context;
import android.os.StrictMode;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.billy.android.loading.Gloading;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import cn.weiben.buildingshopping.base.GlobalClickCallbacks;
import cn.weiben.buildingshopping.base.loading.SpecialAdapter;
import cn.weiben.buildingshopping.crash.CrashManager;

/**
 * Created by Administrator on 2019/2/27.
 */

public class App extends MultiDexApplication {

    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorAccent, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header（new BezierCircleHeader(context)）
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //异常处理初始化
        CrashManager.init(mContext);

        //初始化全局防重复点击
//        GlobalClickCallbacks.init(this);

        //Init the default loading status view for global usage wrap a
        //customer Adapter implementation
        Gloading.debug(BuildConfig.DEBUG);
        Gloading.initDefault(new SpecialAdapter());


        fixAndroidMTakePhotoQuestion();

    }


    /**
     * 方案一：android 7.0系统解决拍照的问题
     * 方案二：继承 FileProvider
     * https://stackoverflow.com/questions/38200282/android-os-fileuriexposedexception-file-storage-emulated-0-test-txt-exposed
     */
    private void fixAndroidMTakePhotoQuestion() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }


}
