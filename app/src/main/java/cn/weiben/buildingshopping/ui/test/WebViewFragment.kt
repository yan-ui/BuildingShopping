package cn.weiben.buildingshopping.ui.test

import android.view.ViewGroup
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.fragment.BaseWebViewMVPFragment
import kotlinx.android.synthetic.main.activity_web_test.*

class WebViewFragment : BaseWebViewMVPFragment<NullPresenter>(){

    override fun getAgentWebParent(): ViewGroup {
        return container
    }

    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.activity_web_test
    }

    private var url:String = ""
    override fun initView() {
        val isHtml = mBundle.getBoolean("isHtml",false)
        if (isHtml) {
            val html = mBundle.getString("content")
            loadHtml(html)
        } else {
            url = mBundle.getString("url","")
            loadUrl(url)
        }
    }


    override fun getIndicatorHeight(): Int {
        return 3
    }

    override fun getIndicatorColor(): Int {
        return resources.getColor(R.color.colorAccent)
    }

    override fun lazyData() {

    }


}

