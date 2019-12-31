package cn.weiben.buildingshopping.base.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.base.interfaces.OnStopLoadListener;


/**
 * 基础http网络列表的Activity
 *
 * @param <P>  Presenter类
 * @param <T>  数据模型(model/JavaBean)类
 * @param <VH> BaseViewHolder或其子类
 * @param <A>  管理RV的Adapter
 * @see #initSmartRefreshLayout(SmartRefreshLayout)
 * @see #initRecyclerView(RecyclerView)
 * @see #getListAsync(int)
 * @see #onLoadSucceed(int, List)
 * @see <pre>
 *       基础使用：
 *       extends BaseHttpRecyclerMVPActivity 并在子类initView中 initSmartRefreshLayout、initRecyclerView、 srlBaseHttpRecycler.autoRefresh()
 *
 *       列表数据加载及显示过程：
 *       1.initSmartRefreshLayout初始化下拉刷新控件
 *       2.initRecyclerView初始化列表控件
 *       3.srlBaseHttpRecycler.autoRefresh触发刷新
 *       4.getListAsync异步获取列表数据
 *       5.onLoadSuccess处理获取数据的结果
 *       6.setList把列表数据绑定到adapter
 *   </pre>
 */
public abstract class BaseHttpRecyclerMVPActivity<P extends BaseContract.BasePresenter, T, VH extends BaseViewHolder, A extends BaseQuickAdapter<T, VH>>
        extends BaseRecyclerMVPActivity<P, T, VH, A>
        implements OnStopLoadListener, OnRefreshListener {


    protected SmartRefreshLayout srlBaseHttpRecycler;

    protected void initSmartRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        srlBaseHttpRecycler = smartRefreshLayout;
        setOnStopLoadListener(this);

        srlBaseHttpRecycler.setEnableLoadMore(false);
        srlBaseHttpRecycler.setOnRefreshListener(this);
    }

    @Override
    public void setAdapter(A adapter) {
        adapter.setOnLoadMoreListener(this::onLoadMore, rvBaseRecycler);
        adapter.disableLoadMoreIfNotFullPage(rvBaseRecycler);
        super.setAdapter(adapter);
    }


    /**
     * @param page 用-page作为requestCode
     */
    @Override
    public abstract void getListAsync(int page);


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefresh();
    }

    @Override
    public void onStopRefresh() {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                srlBaseHttpRecycler.finishRefresh();
            }
        });
    }

    @Override
    public void onStopLoadMore(final boolean isHaveMore) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (isHaveMore) {
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }
        });
    }



    /**
     * 处理网络请求加载失败结果
     *
     * @param page
     * @param e
     */
    @Override
    public synchronized void onLoadFailed(int page, Exception e) {
        super.onLoadFailed(page, e);
        if (adapter != null) {
            adapter.loadMoreFail();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        srlBaseHttpRecycler = null;
    }
}