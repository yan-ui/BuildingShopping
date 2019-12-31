package cn.weiben.buildingshopping.ui.test.list

import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseHttpRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_http_recycler_test.*

class HttpRecyclerTestActivity : BaseHttpRecyclerMVPActivity<ListPresenter, NewsBean, BaseViewHolder, TestRVAdapter>(), ListContract.View {

    override fun initPresenter(): ListPresenter {
        return ListPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_http_recycler_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("继承 BaseHttpRecyclerMVPActivity 使用")
        setNavigationImage()
        setNavigationOnClickListener { finish() }

        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)

        mSmartRefreshLayout.autoRefresh()
    }


    override fun getListAsync(page: Int) {
        mPresenter.getListNoLoading(page)
    }

    private var testFail = true
    override fun setData(page: Int, list: MutableList<NewsBean>?) {
        if (list != null) {
            /**
             *   此处为了测试网络请求加载失败等异常情况重试
             *    =======     不要照搬     ========
             */
            if (page == 2 && testFail) {
                testFail = false
                onLoadFailed(page, null)
            } else {
                onLoadSucceed(page, page >= 4, list)

                /**
                 * 此方法  与 上面的可以任选一种
                 * 不同之处在于 上面的方法需要自己判断是否加载完成，没有更多了
                 * 而下面的方法是根据下一页返回的数据是否为空来判断是否加载完成
                 * 分实际情况选择调用
                 */
                //onLoadSucceed(page, list)
            }

        } else {
            onLoadFailed(page, null)
        }
    }

    override fun setList(list: MutableList<NewsBean>?) {
        setList(object : AdapterCallBack<TestRVAdapter> {

            override fun createAdapter(): TestRVAdapter {
                return TestRVAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }


}
