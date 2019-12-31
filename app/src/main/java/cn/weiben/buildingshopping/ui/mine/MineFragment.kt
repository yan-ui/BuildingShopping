package cn.weiben.buildingshopping.ui.mine

import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseFragment
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment

class MineFragment : BaseMVPFragment<MinePresenter>(), MineContract.View {
    override fun initPresenter(): MinePresenter {
        return MinePresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_mine
    }

    override fun lazyData() {

    }

    override fun initView() {
        mPresenter.getUser()
    }


}
