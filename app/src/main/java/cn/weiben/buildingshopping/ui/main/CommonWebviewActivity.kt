package cn.weiben.buildingshopping.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseWebViewMVPActivity
import kotlinx.android.synthetic.main.activity_common_webview.*

class CommonWebviewActivity : BaseWebViewMVPActivity<NullPresenter>() {

    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }

    override fun getAgentWebParent(): ViewGroup {
        return container
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_common_webview
    }

    private var url: String = ""
    private var isHtml: Boolean = false
    override fun initView(savedInstanceState: Bundle?) {
        url = intent.getStringExtra("url")
        loadUrl(url)
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
