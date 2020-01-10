package cn.weiben.buildingshopping.ui.mine.address_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseHttpRecyclerMVPActivity
import cn.weiben.buildingshopping.base.activity.BaseRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.AddressModel
import cn.weiben.buildingshopping.ui.adapter.AddressListRvAdapter
import cn.weiben.buildingshopping.ui.adapter.TestRVAdapter
import cn.weiben.buildingshopping.ui.mine.address_manager.add_address.AddAddressActivity
import cn.weiben.buildingshopping.ui.mine.leave_message.add_message.AddMessageContract
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_address_manager.*
import kotlinx.android.synthetic.main.activity_address_manager.mRecyclerView
import kotlinx.android.synthetic.main.activity_address_manager.mSmartRefreshLayout
import kotlinx.android.synthetic.main.activity_http_recycler_test.*

class AddressManagerActivity : BaseRecyclerMVPActivity<AddressManagerPresenter, AddressModel, BaseViewHolder, AddressListRvAdapter>(), AddressManagerContract.View {


    override fun initPresenter(): AddressManagerPresenter {
        return AddressManagerPresenter()
    }


    override fun getActivityLayoutID(): Int {
        return R.layout.activity_address_manager
    }

    override fun getListAsync(page: Int) {
        mPresenter.getAddressList()
    }

    override fun initView(savedInstanceState: Bundle?) {
        val type = intent.getIntExtra("type", 0)
        if (type != 0) {
            setTitle("选择收货地址")
        } else {
            setTitle("收货地址管理")
        }

        initRecyclerView(mRecyclerView)
        mSmartRefreshLayout.setOnRefreshListener {
            onRefresh()
        }


        mRecyclerView.addItemDecoration(RecycleViewDivider(this, LinearLayout.VERTICAL, ConvertUtils.dp2px(10f),
                resources.getColor(R.color.background_color_gray)))

        btnAddAddress.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        mSmartRefreshLayout.autoRefresh()
    }

    override fun setAddressList(datas: MutableList<AddressModel>?) {
        mSmartRefreshLayout.finishRefresh()
        if (datas != null) {
            onLoadSucceed(1, datas)
        } else {
            onLoadFailed(1, null)
        }
    }


    override fun setList(list: MutableList<AddressModel>?) {
        setList(object : AdapterCallBack<AddressListRvAdapter> {

            override fun createAdapter(): AddressListRvAdapter {
                return AddressListRvAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }

    override fun deleteSuccess() {
        mSmartRefreshLayout.autoRefresh()
    }

    override fun setDefaultSuccess() {
        mSmartRefreshLayout.autoRefresh()
    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        super.onItemChildClick(adapter, view, position)
        val bena = adapter.data[position] as AddressModel
        when (view.id) {
            R.id.btnEdit -> {
                val intent = Intent(this,AddAddressActivity::class.java)
                intent.putExtra("type",1)
                intent.putExtra("address_id",bena.address_id)
                startActivity(intent)
            }

            R.id.btnDelete -> {
                mPresenter.deleteAddress(bena.address_id)
            }

            R.id.rbDefault -> {
                mPresenter.setDefaultAddress(bena.address_id)
            }
        }

    }

}
