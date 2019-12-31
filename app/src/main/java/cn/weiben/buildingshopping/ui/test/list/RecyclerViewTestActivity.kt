package cn.weiben.buildingshopping.ui.test.list

import android.os.Bundle
import android.view.View
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewTestActivity : BaseRecyclerMVPActivity<ListPresenter, NewsBean, BaseViewHolder, TestRVAdapter>(), ListContract.View {


    override fun initPresenter(): ListPresenter {
        return ListPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_recycler_view
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("继承 BaseRecyclerMVPActivity 页面")
        setNavigationImage()
        setNavigationOnClickListener { finish() }

        initRecyclerView(mRecyclerView)

        onRefresh()
    }

    override fun getListAsync(page: Int) {
        LogUtils.e("==========================                0  ")
        mPresenter.getList(page)
    }

    override fun setData(page: Int, list: MutableList<NewsBean>?) {
        LogUtils.e(Gson().toJson(list))
        if (list != null) {
            onLoadSucceed(page, list)
        } else {
            onLoadFailed(page, null)
        }
    }

    override fun setList(list: MutableList<NewsBean>?) {
        LogUtils.e("==========================                1  ")
        setList(object : AdapterCallBack<TestRVAdapter> {

            override fun createAdapter(): TestRVAdapter {
                return TestRVAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        super.onItemClick(adapter, view, position)
        ToastUtils.showShort("点击了Item $position")
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        super.onItemChildClick(adapter, view, position)
        ToastUtils.showShort("点击了图片 $position")
    }

    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int): Boolean {
        ToastUtils.showShort("长按了Item $position")
        return true
    }

}
