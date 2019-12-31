package cn.weiben.buildingshopping.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseBottomMVPTabActivity
import cn.weiben.buildingshopping.ui.cart.CartFragment
import cn.weiben.buildingshopping.ui.category.CategoryFragment
import cn.weiben.buildingshopping.ui.home.HomeFragment
import cn.weiben.buildingshopping.ui.mine.MineFragment
import cn.weiben.buildingshopping.ui.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseBottomMVPTabActivity<NullPresenter>() {

    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }


    override fun getActivityLayoutID(): Int {
        return R.layout.activity_main
    }


    override fun isShowTitleBar(): Boolean {
        return false
    }

    override fun isUseStatusBarContent(): Boolean {
        return true
    }

    private var mFragments: MutableList<Fragment>? = null
    override fun initView(savedInstanceState: Bundle?) {
        toggleStatusBarMode(false)
        mFragments = ArrayList()
        mFragments!!.add(HomeFragment())
        mFragments!!.add(CategoryFragment())
        mFragments!!.add(ShopFragment())
        mFragments!!.add(CartFragment())
        mFragments!!.add(MineFragment())

        addFragments(mFragments!!, R.id.mainContainer)


        bottomNavigationView.enableAnimation(false)
        bottomNavigationView.enableShiftingMode(false)
        bottomNavigationView.enableItemShiftingMode(false)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> showCurrentFragment(0)
                R.id.navigation_category -> showCurrentFragment(1)
                R.id.navigation_shop -> showCurrentFragment(2)
                R.id.navigation_cart -> showCurrentFragment(3)
                R.id.navigation_mine -> showCurrentFragment(4)
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

}
