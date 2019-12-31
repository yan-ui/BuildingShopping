package cn.weiben.buildingshopping.ui.test.list

import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_rvtest.*

class RVTestFragment : BaseRecyclerMVPFragment<ListPresenter, NewsBean, BaseViewHolder, TestRVAdapter>(), ListContract.View {

    override fun initPresenter(): ListPresenter {
        return ListPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_rvtest
    }

    override fun initView() {
        initRecyclerView(mRecyclerView)
    }

    override fun lazyData() {
        onRefresh()
    }

    override fun getListAsync(page: Int) {
        mPresenter.getList(page)
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
