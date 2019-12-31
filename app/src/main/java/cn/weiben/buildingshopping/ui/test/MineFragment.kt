package cn.weiben.buildingshopping.ui.test

import android.content.Intent
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseFragment
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.ui.test.list.HttpRecyclerTestActivity
import cn.weiben.buildingshopping.ui.test.list.RVFragmentTestActivity
import cn.weiben.buildingshopping.ui.test.list.RecyclerViewTestActivity
import kotlinx.android.synthetic.main.activity_mine_test.*

class MineFragment : BaseFragment() {

    override fun getFragmentLayoutID(): Int {
        return R.layout.activity_mine_test
    }

    override fun initView() {

        image.setOnClickListener {
            GlideManager.loadRoundImg("", image)//http://qiniu.dashuiniu.com.cn//qiniu/20191029/FhGjxrTW6xtxwNJj9K2_nuDmslIK.jpg
        }

        btnActivity.setOnClickListener {
            startActivity(Intent(mActivity, RecyclerViewTestActivity::class.java))
        }

        btnHttpActivity.setOnClickListener {
            startActivity(Intent(mActivity, HttpRecyclerTestActivity::class.java))
        }

        btnFragment.setOnClickListener {
            startActivity(Intent(mActivity, RVFragmentTestActivity::class.java))
        }

    }


    override fun lazyData() {

    }


}

