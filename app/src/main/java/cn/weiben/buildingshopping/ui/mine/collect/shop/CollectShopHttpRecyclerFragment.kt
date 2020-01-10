package cn.weiben.buildingshopping.ui.mine.collect.shop

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseHttpRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.CollectGoodsBean
import cn.weiben.buildingshopping.model.CollectShopBean
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.model.OrderResultBean
import cn.weiben.buildingshopping.ui.adapter.CollectGoodsRvAdapter
import cn.weiben.buildingshopping.ui.adapter.CollectShopRvAdapter
import cn.weiben.buildingshopping.ui.adapter.OrderHttpRecyclerAdapter
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import cn.weiben.buildingshopping.ui.shop.shop_details.ShopDetailsActivity
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_http_recycler_test.*
import kotlinx.android.synthetic.main.activity_http_recycler_test.mRecyclerView
import kotlinx.android.synthetic.main.activity_http_recycler_test.mSmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_shop.*

class CollectShopHttpRecyclerFragment : BaseHttpRecyclerMVPFragment<CollectShopListPresenter, CollectShopBean, BaseViewHolder, CollectShopRvAdapter>(), CollectShopListContract.View {


    override fun initPresenter(): CollectShopListPresenter {
        return CollectShopListPresenter()
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
        mPresenter.getCollectShopList(page)
    }


    override fun setData(page: Int, list: MutableList<CollectShopBean>?) {
        if (list != null) {
            onLoadSucceed(page, list)
        } else {
            onLoadFailed(page, null)
        }
    }

    override fun setList(list: MutableList<CollectShopBean>?) {
        setList(object : AdapterCallBack<CollectShopRvAdapter> {

            override fun createAdapter(): CollectShopRvAdapter {
                return CollectShopRvAdapter(list)
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
        val bean = adapter.data[position] as CollectShopBean

        val intent = Intent(mActivity,ShopDetailsActivity::class.java)
        intent.putExtra("id",bean.supplierid)
        startActivity(intent)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemChildClick(adapter, view, position)

        val bean = adapter.data[position] as CollectShopBean
        when (view.id) {
            R.id.btnDelete -> {
                mPresenter.delete(position, bean.id)
            }
        }

    }


}
