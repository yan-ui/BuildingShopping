package cn.weiben.buildingshopping.ui.test.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseTabMVPActivity
import kotlinx.android.synthetic.main.activity_rvfragment_test.*

class RVFragmentTestActivity : BaseTabMVPActivity<NullPresenter>() {
    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_rvfragment_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("RecyclerViewFragment 使用")
        setNavigationImage()
        setNavigationOnClickListener { finish() }

        val tabList = arrayListOf("Base","BaseHttp")

        val tabs = ArrayList<Fragment>()
        tabs.add(RVTestFragment())
        tabs.add(RVHttpTestFragment())

        addFragments(tabs,tabList,mViewPager,mMagicIndicator)
    }



}
