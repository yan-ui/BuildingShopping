package cn.weiben.buildingshopping.ui.shop

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.fragment.BaseFragment
import cn.weiben.buildingshopping.base.fragment.BaseHttpRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.ShopBean
import cn.weiben.buildingshopping.ui.adapter.ShopBeanRvAdapter
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity
import cn.weiben.buildingshopping.ui.shop.shop_details.ShopDetailsActivity
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : BaseHttpRecyclerMVPFragment<ShopPresenter, ShopBean.SupplierListBean, BaseViewHolder, ShopBeanRvAdapter>(), ShopContract.View {

    override fun initPresenter(): ShopPresenter {
        return ShopPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_shop
    }

    override fun lazyData() {

    }

    override fun initView() {
        shopTitleBar.setBackgroundResource(R.color.colorAccent)

        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)
        mRecyclerView.addItemDecoration(RecycleViewDivider(mActivity,LinearLayout.VERTICAL,ConvertUtils.dp2px(10f),
        resources.getColor(R.color.background_color_gray)))

        mSmartRefreshLayout.autoRefresh()
    }

    override fun onRetry() {
        super.onRetry()
        mSmartRefreshLayout.autoRefresh()
    }


    override fun getListAsync(page: Int) {
        mPresenter.getShopBean(page)
    }

    override fun setShopBean(page: Int, bean: ShopBean?) {
        if (bean != null && bean.supplier_list != null) {
            onLoadSucceed(page, bean.supplier_list)
        } else {
            onLoadFailed(page, null)
        }
    }


    override fun setList(list: MutableList<ShopBean.SupplierListBean>?) {
        setList(object : AdapterCallBack<ShopBeanRvAdapter> {

            override fun createAdapter(): ShopBeanRvAdapter {
                return ShopBeanRvAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemClick(adapter, view, position)
        val bean = (adapter.data[position] as ShopBean.SupplierListBean)
        val intent = Intent(mActivity,ShopDetailsActivity::class.java)
        intent.putExtra("id",bean.supplier_id)
        startActivity(intent)
    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemChildClick(adapter, view, position)
        val bean = (adapter.data[position] as ShopBean.SupplierListBean)
        when(view.id){
            R.id.btnCall ->{
                val intent = Intent()
                intent.action = Intent.ACTION_DIAL
                intent.data = Uri.parse("tel:" + bean.contacts_phone)
                startActivity(intent)
            }

            R.id.btnMap ->{
                val intent = Intent(mActivity,CommonWebviewActivity::class.java)
                intent.putExtra("url",bean.mapUrl)
                startActivity(intent)
            }

        }


    }

}
