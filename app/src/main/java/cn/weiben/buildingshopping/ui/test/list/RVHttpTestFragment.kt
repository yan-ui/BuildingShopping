package cn.weiben.buildingshopping.ui.test.list

import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseHttpRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_http_recycler_test.*

class RVHttpTestFragment : BaseHttpRecyclerMVPFragment<ListPresenter, NewsBean, BaseViewHolder, TestRVAdapter>(), ListContract.View {

    override fun initPresenter(): ListPresenter {
        return ListPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.activity_http_recycler_test
    }

    override fun initView() {
        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)
    }

    override fun lazyData() {
        mSmartRefreshLayout.autoRefresh()
    }

    override fun getListAsync(page: Int) {
        mPresenter.getListNoLoading(page)
    }

    override fun setData(page: Int, list: MutableList<NewsBean>?) {
        if(list != null){
            onLoadSucceed(page,list)
        }else{
            onLoadFailed(page,null)
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
