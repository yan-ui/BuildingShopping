package cn.weiben.buildingshopping.ui.mine.order_list

import android.content.Intent
import android.net.Uri
import android.view.View
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseHttpRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.NewsBean
import cn.weiben.buildingshopping.model.OrderResultBean
import cn.weiben.buildingshopping.ui.adapter.OrderHttpRecyclerAdapter
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_http_recycler_test.*

class OrderHttpRecyclerFragment : BaseHttpRecyclerMVPFragment<OrderListPresenter, OrderResultBean.OrderListBean, BaseViewHolder, OrderHttpRecyclerAdapter>(), OrderListContract.View {


    override fun initPresenter(): OrderListPresenter {
        return OrderListPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.activity_http_recycler_test
    }


    private var type = 100
    override fun initView() {
        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)

        type = mBundle.getInt("type", -1)

    }

    override fun onRetry() {
        super.onRetry()
        lazyData()
    }

    override fun lazyData() {
        mSmartRefreshLayout.autoRefresh()
    }

    override fun getListAsync(page: Int) {
        mPresenter.getOrderList(page, type)
    }

    override fun setData(page: Int, list: MutableList<OrderResultBean.OrderListBean>?) {
        if (list != null) {
            onLoadSucceed(page, list)
        } else {
            onLoadFailed(page, null)
        }
    }

    override fun setList(list: MutableList<OrderResultBean.OrderListBean>?) {
        setList(object : AdapterCallBack<OrderHttpRecyclerAdapter> {

            override fun createAdapter(): OrderHttpRecyclerAdapter {
                return OrderHttpRecyclerAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemChildClick(adapter, view, position)

        val bean = adapter.data[position] as OrderResultBean.OrderListBean
        when(view.id){
            R.id.btnCall ->{
                val intent = Intent()
                intent.action = Intent.ACTION_DIAL
                intent.data = Uri.parse("tel:" + bean.tel)
                startActivity(intent)
            }

            R.id.btnCancelOrder ->{
                mPresenter.cancelOrder(bean.order_id)
            }


        }


    }

    override fun cancelOrderSuccess() {
        mSmartRefreshLayout.autoRefresh()
    }

}
