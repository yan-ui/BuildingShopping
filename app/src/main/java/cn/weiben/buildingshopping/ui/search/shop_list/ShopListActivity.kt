package cn.weiben.buildingshopping.ui.search.shop_list

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseHttpRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.ShopBean
import cn.weiben.buildingshopping.ui.adapter.ShopBeanRvAdapter
import cn.weiben.buildingshopping.ui.adapter.ShopSearchBeanRvAdapter
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_sort_list.*

class ShopListActivity : BaseHttpRecyclerMVPActivity<ShopPresenter, ShopBean.SupplierListBean, BaseViewHolder, ShopSearchBeanRvAdapter>(), ShopContract.View {

    override fun initPresenter(): ShopPresenter {
        return ShopPresenter()
    }


    override fun getActivityLayoutID(): Int {
        return R.layout.activity_shop_list
    }

    private lateinit var keywords: String

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("搜索结果")
        keywords = intent.getStringExtra("search")

        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)
        mRecyclerView.addItemDecoration(RecycleViewDivider(this, LinearLayout.VERTICAL, ConvertUtils.dp2px(10f),
                resources.getColor(R.color.background_color_gray)))

        mSmartRefreshLayout.autoRefresh()
    }


    override fun onRetry() {
        super.onRetry()
        mSmartRefreshLayout.autoRefresh()
    }

    override fun setList(list: MutableList<ShopBean.SupplierListBean>?) {
        setList(object : AdapterCallBack<ShopSearchBeanRvAdapter> {

            override fun createAdapter(): ShopSearchBeanRvAdapter {
                return ShopSearchBeanRvAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })

    }


    override fun getListAsync(page: Int) {
        mPresenter.getShopBean(page, keywords)
    }

    override fun setShopBean(page: Int, data: List<ShopBean.SupplierListBean>?) {
        if (data != null) {
            onLoadSucceed(page, data)
        } else {
            onLoadFailed(page, null)
        }
    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemChildClick(adapter, view, position)

        val bean = (adapter.data[position] as ShopBean.SupplierListBean)

        when(view.id){
            R.id.btnCollect ->{
                mPresenter.collectShop(bean.supplier_id)
            }
        }

    }

}
