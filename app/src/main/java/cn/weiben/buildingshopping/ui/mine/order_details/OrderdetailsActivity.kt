package cn.weiben.buildingshopping.ui.mine.order_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity

class OrderdetailsActivity : BaseMVPActivity<NullPresenter>() {
    override fun initPresenter(): NullPresenter {
        return NullPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_orderdetails
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}
