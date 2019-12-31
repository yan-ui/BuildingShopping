package cn.weiben.buildingshopping.ui.home.ad_details

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseWebViewMVPActivity
import cn.weiben.buildingshopping.model.NewDetails
import kotlinx.android.synthetic.main.activity_common_web_view.*

class CommonWebViewHtmlActivity : BaseWebViewMVPActivity<NewDetailsPresenter>(), NewDetailsContract.View {

    override fun initPresenter(): NewDetailsPresenter {
        return NewDetailsPresenter()
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


    override fun initView(savedInstanceState: Bundle?) {
        val id = intent.getStringExtra("id")
        mPresenter.getNewDetails(id)

    }

    override fun setNewDetails(bean: NewDetails?) {
        if (bean == null) {
            return
        }
        setTitle(bean.info.title)
        loadHtml(bean.info.content)
    }


}
