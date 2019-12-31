package cn.weiben.buildingshopping.base.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack;
import cn.weiben.buildingshopping.base.interfaces.CacheCallBack;
import cn.weiben.buildingshopping.base.interfaces.OnStopLoadListener;
import cn.weiben.buildingshopping.common.Contacts;
import cn.weiben.buildingshopping.manager.CacheManager;


/**
 * 基础RecyclerView Fragment
 *
 * @param <P>  Presenter类
 * @param <T>  数据模型(model/JavaBean)类
 * @param <VH> BaseViewHolder或其子类
 * @param <A>  管理RV的BaseQuickAdapter
 * @see #rvBaseRecycler
 * @see #initCache
 * @see #getListAsync
 * @see #onRefresh
 * @see <pre>
 *       基础使用：
 *       extends BaseRecyclerMVPFragment 并在子类initView()中调用 initRecyclerView(...) , onRefresh(...)
 *       列表数据加载及显示过程：
 *       1.initRecyclerView 初始化RV控件
 *       2.onRefresh触发刷新
 *       3.getListAsync异步获取列表数据
 *       4.onLoadSucceed处理获取数据的结果
 *       5.setList把列表数据绑定到adapter
 *   </pre>
 */
public abstract class BaseRecyclerMVPFragment<P extends BaseContract.BasePresenter, T, VH extends BaseViewHolder, A extends BaseQuickAdapter<T, VH>>
        extends BaseMVPFragment<P> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemLongClickListener {

    private static final String TAG = "BaseRecyclerMVPFragment";

    /**
     * 显示列表的RecyclerView
     *
     * @warn 只使用rvBaseRecycler为显示列表数据的AbsRecyclerView(RecyclerView, GridView等)，不要在子类中改变它
     */
    protected RecyclerView rvBaseRecycler;
    /**
     * 管理RV的Item的Adapter
     */
    protected A adapter;

    private boolean isToSaveCache;
    private boolean isToLoadCache;

    private boolean isSucceed = false;

    private int saveCacheStart;

    /**
     * 列表首页页码。有些服务器设置为0，即列表页码从0开始
     */
    public static final int PAGE_NUM_1 = 0;

    /**
     * 数据列表
     */
    private List<T> list;
    /**
     * 正在加载
     */
    protected boolean isLoading = false;
    /**
     * 还有更多可加载数据
     */
    protected boolean isHaveMore = true;
    /**
     * 加载页码，每页对应一定数量的数据
     */
    private int page;
    private int loadCacheStart;


    public void initRecyclerView(RecyclerView recyclerView) {
        rvBaseRecycler = recyclerView;
        rvBaseRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
    }


    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(A adapter) {
        this.adapter = adapter;

        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemLongClickListener(this);

        rvBaseRecycler.setAdapter(adapter);
        adapter.setEmptyView(R.layout.common_empty_view, rvBaseRecycler);
        adapter.setHeaderAndEmpty(true);
    }

    /**
     * 刷新列表数据（已在UI线程中），一般需求建议直接调用setList(List<T> l, AdapterCallBack<A> callBack)
     *
     * @param list
     */
    public abstract void setList(List<T> list);

    /**
     * 显示列表（已在UI线程中）
     *
     * @param callBack
     */
    public void setList(AdapterCallBack<A> callBack) {
        if (adapter == null) {
            setAdapter(callBack.createAdapter());
        } else {
            callBack.refreshAdapter();
        }
    }


    /**
     * 获取列表，在非UI线程中
     *
     * @param page 在onLoadSucceed中传回来保证一致性
     * @must 获取成功后调用onLoadSucceed
     */
    public abstract void getListAsync(int page);

    private CacheCallBack<T> cacheCallBack;

    /**
     * 初始化缓存
     *
     * @param cacheCallBack
     * @warn 在initData前使用才有效
     */
    protected void initCache(CacheCallBack<T> cacheCallBack) {
        this.cacheCallBack = cacheCallBack;
        isToSaveCache = Contacts.cache && cacheCallBack != null && cacheCallBack.getCacheClass() != null;
        isToLoadCache = isToSaveCache && !StringUtils.isEmpty(cacheCallBack.getCacheGroup());

    }


    /**
     * 刷新（从头加载）
     *
     * @must 在子类onCreate中调用，建议放在最后
     */
    public void onRefresh() {
        loadData(PAGE_NUM_1);
    }

    /**
     * 加载更多
     */
    public void onLoadMore() {
        if (!isSucceed && page <= PAGE_NUM_1) {
            Log.w(TAG, "onLoadMore  isSucceed == false && page <= PAGE_NUM_1 >> return;");
            return;
        }
        loadData(page + (isSucceed ? 1 : 0));
    }

    public void loadData(int page) {
        loadData(page, isToLoadCache);
    }

    /**
     * 加载数据，用getListAsync方法发请求获取数据
     *
     * @param page_
     * @param isCache
     */
    private void loadData(int page_, final boolean isCache) {
        if (isLoading) {
            Log.w(TAG, "loadData  isLoading >> return;");
            return;
        }
        isLoading = true;
        isSucceed = false;

        if (page_ <= PAGE_NUM_1) {
            page_ = PAGE_NUM_1;
            isHaveMore = true;
            loadCacheStart = 0;//使用则可像网络正常情况下的重载，不使用则在网络异常情况下不重载（导致重载后加载数据下移）
        } else {
            if (!isHaveMore) {
                stopLoadData(page_);
                return;
            }
            loadCacheStart = list == null ? 0 : list.size();
        }
        this.page = page_;
        Log.i(TAG, "loadData  page_ = " + page_ + "; isCache = " + isCache
                + "; isHaveMore = " + isHaveMore + "; loadCacheStart = " + loadCacheStart);

        runThread(TAG + "loadData", new Runnable() {

            @Override
            public void run() {
                if (!isCache) {//从网络获取数据
                    getListAsync(page);
                } else {//从缓存获取数据
                    onLoadSucceed(page, CacheManager.getInstance().getList(cacheCallBack.getCacheClass()
                            , cacheCallBack.getCacheGroup(), loadCacheStart, cacheCallBack.getCacheCount()),
                            true);
                    if (page <= PAGE_NUM_1) {
                        isLoading = false;//stopLoadeData在其它线程isLoading = false;后这个线程里还是true
                        loadData(page, false);
                    }
                }
            }
        });
    }


    /**
     * 停止加载数据
     * isCache = false;
     *
     * @param page
     */
    public synchronized void stopLoadData(int page) {
        stopLoadData(page, false);
    }

    /**
     * 停止加载数据
     *
     * @param page
     * @param isCache
     */
    private synchronized void stopLoadData(int page, boolean isCache) {
        Log.i(TAG, "stopLoadData  isCache = " + isCache);
        isLoading = false;

        if (isCache) {
            Log.d(TAG, "stopLoadData  isCache >> return;");
            return;
        }

        if (onStopLoadListener == null) {
            Log.w(TAG, "stopLoadData  onStopLoadListener == null >> return;");
            return;
        }
        onStopLoadListener.onStopRefresh();
        if (page > PAGE_NUM_1) {
            onStopLoadListener.onStopLoadMore(isHaveMore);
        }
    }


    /**
     * 处理列表
     *
     * @param page
     * @param newList 新数据列表
     * @param isCache
     * @return
     */
    public synchronized void handleList(int page, List<T> newList, boolean isCache) {
        if (newList == null) {
            newList = new ArrayList<T>();
        }
        isSucceed = !newList.isEmpty();
        Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<\n handleList  newList.size = " + newList.size() + "; isCache = " + isCache
                + "; page = " + page + "; isSucceed = " + isSucceed);

        if (page <= PAGE_NUM_1) {
            Log.i(TAG, "handleList  page <= PAGE_NUM_1 >>>>  ");
            saveCacheStart = 0;
            list = new ArrayList<T>(newList);
            if (!isCache && !list.isEmpty()) {
                Log.i(TAG, "handleList  isCache == false && list.isEmpty() == false >>  isToLoadCache = false;");
                isToLoadCache = false;
            }
        } else {
            Log.i(TAG, "handleList  page > PAGE_NUM_1 >>>>  ");
            if (list == null) {
                list = new ArrayList<T>();
            }
            saveCacheStart = list.size();
            isHaveMore = !newList.isEmpty();
            if (isHaveMore) {
                list.addAll(newList);
            }
        }

        Log.i(TAG, "handleList  list.size = " + list.size() + "; isHaveMore = " + isHaveMore
                + "; isToLoadCache = " + isToLoadCache + "; saveCacheStart = " + saveCacheStart
                + "\n>>>>>>>>>>>>>>>>>>\n\n");
    }


    /**
     * 加载成功
     * isCache = false;
     *
     * @param page
     * @param newList
     */
    public synchronized void onLoadSucceed(final int page, final List<T> newList) {
        onLoadSucceed(page, newList, false);
    }

    /**
     * 加载成功
     * isCache = false;
     *
     * @param page    当前页码
     * @param isEnd   是否还有更多
     * @param newList 列表数据
     */
    public synchronized void onLoadSucceed(final int page, boolean isEnd, final List<T> newList) {
        onLoadSucceed(page, newList, false, isEnd);
    }

    /**
     * 加载成功
     *
     * @param page
     * @param newList
     * @param isCache newList是否为缓存
     */
    public synchronized void onLoadSucceed(final int page, final List<T> newList, final boolean isCache) {
        onLoadSucceed(page, newList, isCache, false);
    }


    private synchronized void onLoadSucceed(final int page, final List<T> newList, final boolean isCache, boolean isEnd) {
        runThread(TAG + "onLoadSucceed", new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onLoadSucceed  page = " + page + "; isCache = " + isCache + " >> handleList...");
                handleList(page, newList, isCache);

                if (isEnd) {
                    isHaveMore = false;
                }

                runUiThread(new Runnable() {

                    @Override
                    public void run() {
                        stopLoadData(page, isCache);
                        setList(list);
                    }
                });

                if (isToSaveCache && isCache == false) {
                    saveCache(newList);
                }
            }
        });
    }

    /**
     * 加载失败
     *
     * @param page
     * @param e
     */
    public synchronized void onLoadFailed(int page, Exception e) {
        Log.e(TAG, "onLoadFailed page = " + page + "; e = " + (e == null ? null : e.getMessage()));
        stopLoadData(page);
    }


    /**
     * 保存缓存
     *
     * @param newList
     */
    public synchronized void saveCache(List<T> newList) {
        if (cacheCallBack == null || newList == null || newList.isEmpty()) {
            Log.e(TAG, "saveCache  cacheCallBack == null || newList == null || newList.isEmpty() >> return;");
            return;
        }

        LinkedHashMap<String, T> map = new LinkedHashMap<String, T>();
        for (T data : newList) {
            if (data != null) {
                map.put(cacheCallBack.getCacheId(data), data);//map.put(null, data);不会崩溃
            }
        }

        CacheManager.getInstance().saveList(cacheCallBack.getCacheClass(), cacheCallBack.getCacheGroup()
                , map, saveCacheStart, cacheCallBack.getCacheCount());
    }
    //缓存>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    private OnStopLoadListener onStopLoadListener;

    /**
     * 设置停止加载监听
     *
     * @param onStopLoadListener
     */
    protected void setOnStopLoadListener(OnStopLoadListener onStopLoadListener) {
        this.onStopLoadListener = onStopLoadListener;
    }


    /**
     * 重写后可自定义对这个事件的处理
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    /**
     * 重写后可自定义对这个事件的处理
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    /**
     * 重写后可自定义对这个事件的处理，如果要在长按后不触发onItemClick，则需要 return true;
     *
     * @param view
     * @param position
     */
    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }


    @Override
    public void onDestroy() {
        isLoading = false;
        isHaveMore = false;
        isToSaveCache = false;
        isToLoadCache = false;

        super.onDestroy();

        rvBaseRecycler = null;
        list = null;

        onStopLoadListener = null;
        cacheCallBack = null;
    }

}