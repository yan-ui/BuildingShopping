package cn.weiben.buildingshopping.ui.test

import android.content.Intent
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment
import cn.weiben.buildingshopping.ui.main.MainContract
import cn.weiben.buildingshopping.ui.main.MainPresenter
import com.vector.update_app_kotlin.updateApp
import kotlinx.android.synthetic.main.second_layout.*
import java.lang.RuntimeException

class SecondFragment : BaseMVPFragment<MainPresenter>(),MainContract.View{


    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.second_layout
    }

    override fun initView() {

        btnZip.setOnClickListener {
            mPresenter.login("","")
        }

        btnUpdate.setOnClickListener {
            mActivity.updateApp().update(true,"1.0.1","http://www.baidu.com/","重大更新",false)
        }

        btnTest.setOnClickListener {
            startActivity(Intent(mActivity, TabTestActivity::class.java))
        }

        btnCrash.setOnClickListener {
            throw RuntimeException("崩溃测试")
        }
    }

    override fun lazyData() {
    }



}