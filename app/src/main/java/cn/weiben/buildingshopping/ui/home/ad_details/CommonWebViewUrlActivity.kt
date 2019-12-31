package cn.weiben.buildingshopping.ui.home.ad_details

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseWebViewMVPActivity
import kotlinx.android.synthetic.main.activity_common_web_view.*

class CommonWebViewUrlActivity : BaseWebViewMVPActivity<NullPresenter>() {
    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }

    override fun getAgentWebParent(): ViewGroup {
        return container
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_common_web_view
    }

    override fun getIndicatorHeight(): Int {
        return 3
    }

    override fun getIndicatorColor(): Int {
        return resources.getColor(R.color.colorAccent)
    }

    override fun setTitle(view: WebView?, title: String?) {
        super.setTitle(view, title)
        setTitle(title)
    }

    override fun initView(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra("url")
        loadUrl(url)
    }

}
