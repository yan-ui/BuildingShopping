package cn.weiben.buildingshopping.ui.mine.collect.goods

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseHttpRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.CollectGoodsBean
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.model.OrderResultBean
import cn.weiben.buildingshopping.ui.adapter.CollectGoodsRvAdapter
import cn.weiben.buildingshopping.ui.adapter.OrderHttpRecyclerAdapter
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_http_recycler_test.*

class CollectGoodsHttpRecyclerFragment : BaseHttpRecyclerMVPFragment<CollectGoodsListPresenter, CollectGoodsBean, BaseViewHolder, CollectGoodsRvAdapter>(), CollectGoodsListContract.View {

    override fun initPresenter(): CollectGoodsListPresenter {
        return CollectGoodsListPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.activity_http_recycler_test
    }


    override fun initView() {
        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)

        mRecyclerView.addItemDecoration(RecycleViewDivider(mActivity, LinearLayout.VERTICAL, ConvertUtils.dp2px(10f),
                resources.getColor(R.color.background_color_gray)))

    }

    override fun onRetry() {
        super.onRetry()
        lazyData()
    }

    override fun lazyData() {
        mSmartRefreshLayout.autoRefresh()
    }

    override fun getListAsync(page: Int) {
        mPresenter.getCollectGoodsList(page)
    }


    override fun setData(page: Int, list: MutableList<CollectGoodsBean>?) {
        if (list != null) {
            onLoadSucceed(page, list)
        } else {
            onLoadFailed(page, null)
        }
    }

    override fun setList(list: MutableList<CollectGoodsBean>?) {
        setList(object : AdapterCallBack<CollectGoodsRvAdapter> {

            override fun createAdapter(): CollectGoodsRvAdapter {
                return CollectGoodsRvAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }


    override fun deleteSuccess(position: Int) {
        adapter.remove(position)
    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View?, position: Int) {
        super.onItemClick(adapter, view, position)
        val bean = adapter.data[position] as CollectGoodsBean

        val intent = Intent(mActivity,GoodsDetailActivity::class.java)
        intent.putExtra("id",bean.goods_id)
        startActivity(intent)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemChildClick(adapter, view, position)

        val bean = adapter.data[position] as CollectGoodsBean
        when (view.id) {
            R.id.tv_delete -> {
                mPresenter.delete(position, bean.rec_id)
            }
        }


    }

}
