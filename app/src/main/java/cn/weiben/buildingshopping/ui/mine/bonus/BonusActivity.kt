package cn.weiben.buildingshopping.ui.mine.bonus

import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseHttpRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.BonusBean
import cn.weiben.buildingshopping.ui.adapter.BonusBeanRvAdapter
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_bonus.*

class BonusActivity : BaseHttpRecyclerMVPActivity<BonusPresenter, BonusBean.BounsBean, BaseViewHolder, BonusBeanRvAdapter>(), BonusContract.View {

    override fun initPresenter(): BonusPresenter {
        return BonusPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_bonus
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("我的红包")

        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)

        btnSubmit.setOnClickListener {
            val input = etInput.text.toString().trim()

            if (input.isEmpty()) {
                ToastUtils.showShort("请输入红包序列号")
                return@setOnClickListener
            }

            mPresenter.addBonus(input)

        }

        mSmartRefreshLayout.autoRefresh()
    }

    override fun setList(list: MutableList<BonusBean.BounsBean>?) {
        setList(object : AdapterCallBack<BonusBeanRvAdapter> {

            override fun createAdapter(): BonusBeanRvAdapter {
                return BonusBeanRvAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }

    override fun getListAsync(page: Int) {
        mPresenter.getBonus(page)
    }


    override fun setBonusList(page: Int, data: List<BonusBean.BounsBean>?) {
        if (data != null) {
            onLoadSucceed(page, data)
        } else {
            onLoadFailed(page, null)
        }
    }

    override fun addBonusSuccess() {
        mSmartRefreshLayout.autoRefresh()
    }

}
