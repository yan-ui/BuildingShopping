package cn.weiben.buildingshopping.ui.test

import android.os.Bundle
import android.os.Handler
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullContract
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_account.*

class PageStatusTestActivity : BaseActivity(), NullContract.View {


    override fun getActivityLayoutID(): Int {
        return R.layout.activity_account
    }


    private lateinit var handler: Handler
    override fun initView(savedInstanceState: Bundle?) {
        setTitle("页面状态管理")
        setNavigationImage()
        setNavigationOnClickListener { finish() }

        handler = Handler()

        showPageLoading()
        handler.postDelayed({
            showSuccess("页面初始化完成")
        }, 1500)


        btnSuccess.setOnClickListener {
            showLoading()
            handler.postDelayed({
                showSuccess("加载成功")
            }, 1500)
        }

        btnFail.setOnClickListener {
            showLoading()
            handler.postDelayed({
                showFailed("加载失败，点击重试")
            }, 1500)
        }

        btnEmpty.setOnClickListener {
            showLoading()
            handler.postDelayed({
                showNoData()
            }, 1500)
        }

    }

    override fun onRetry() {
        super.onRetry()
        showLoading()
        handler.postDelayed({
            showSuccess("重试成功")
        }, 1500)
    }

}