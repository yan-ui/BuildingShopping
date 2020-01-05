package cn.weiben.buildingshopping.ui.category.sort_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseHttpRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.GoodsBean
import cn.weiben.buildingshopping.ui.adapter.SortListGoodsGridRvAdapter
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_sort_list.*

class SortListActivity : BaseHttpRecyclerMVPActivity<SortListPresenter, GoodsBean, BaseViewHolder, SortListGoodsGridRvAdapter>(), SortListContract.View {

    override fun initPresenter(): SortListPresenter {
        return SortListPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_sort_list
    }

    private lateinit var id: String
    private var sort: String = "goods_id"
    private var last = 10
    private var amount = 10
    private var order = "DESC"

    override fun initView(savedInstanceState: Bundle?) {
        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)

        id = intent.getStringExtra("id")

        group.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rbGoodId -> {
                    if (rbGoodId.isChecked) {
                        priceView.restore(false)
                        sort = "goods_id"
                        order = "DESC"
                        mSmartRefreshLayout.autoRefresh()
                    }
                }

                R.id.rbSaleNum -> {
                    if (rbSaleNum.isChecked) {
                        priceView.restore(false)
                        sort = "salenum"
                        order = "DESC"
                        mSmartRefreshLayout.autoRefresh()
                    }
                }

                R.id.rbLastUpdate -> {
                    if (rbLastUpdate.isChecked) {
                        priceView.restore(false)
                        sort = "last_update"
                        order = "DESC"
                        mSmartRefreshLayout.autoRefresh()
                    }
                }
            }
        }

        priceView.setCallback { isUp ->

            group.clearCheck()
            sort = "final_price"
            if (isUp) {
                order = "ASC"
            } else {
                order = "DESC"
            }
            mSmartRefreshLayout.autoRefresh()
        }

        cbMode.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mRecyclerView.layoutManager = GridLayoutManager(this, 2)
            } else {
                mRecyclerView.layoutManager = LinearLayoutManager(this)

            }
            adapter.setGridModel(isChecked)
        }


        mSmartRefreshLayout.autoRefresh()
    }


    override fun onRetry() {
        super.onRetry()
        mSmartRefreshLayout.autoRefresh()
    }

    override fun getListAsync(page: Int) {
        mPresenter.getSortList(id, sort, page, last * page, amount, order)
    }


    override fun setSortList(page: Int, datas: MutableList<GoodsBean>?) {
        if (datas != null) {
            onLoadSucceed(page, datas)
        } else {
            onLoadFailed(page, null)
        }
    }

    override fun setList(list: MutableList<GoodsBean>?) {
        setList(object : AdapterCallBack<SortListGoodsGridRvAdapter> {

            override fun createAdapter(): SortListGoodsGridRvAdapter {
                return SortListGoodsGridRvAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        super.onItemClick(adapter, view, position)
        if (adapter == null) {
            return
        }

        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as GoodsBean
            val intent = Intent(this, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.goods_id)
            startActivity(intent)
        }

    }


}
