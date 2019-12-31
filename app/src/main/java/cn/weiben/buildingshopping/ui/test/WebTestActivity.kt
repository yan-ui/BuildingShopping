package cn.weiben.buildingshopping.ui.test

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseWebViewMVPActivity
import kotlinx.android.synthetic.main.activity_web_test.*

class WebTestActivity : BaseWebViewMVPActivity<NullPresenter>() {
    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }

    override fun getAgentWebParent(): ViewGroup {
        return container
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_web_test
    }

    private var url: String = ""
    private var isHtml: Boolean = false
    override fun initView(savedInstanceState: Bundle?) {
        setTitle("BaseWebViewMVPActivity")
        setNavigationImage()
        setNavigationOnClickListener { finish() }

        isHtml = intent.getBooleanExtra("isHtml", false)
        if (isHtml) {
            val html = intent.getStringExtra("content")
            loadHtml(html)
        } else {
            url = intent.getStringExtra("url")
            loadUrl(url)
        }

    }


    override fun getIndicatorHeight(): Int {
        return 3
    }

    override fun getIndicatorColor(): Int {
        return resources.getColor(R.color.colorAccent)
    }

    override fun setTitle(view: WebView?, title: String?) {
        super.setTitle(view, title)
        if (!isHtml) setTitle(title)

    }

}
