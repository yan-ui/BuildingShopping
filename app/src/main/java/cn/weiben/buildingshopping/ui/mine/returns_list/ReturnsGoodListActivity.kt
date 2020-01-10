package cn.weiben.buildingshopping.ui.mine.returns_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity

class ReturnsGoodListActivity : BaseActivity() {
    override fun getActivityLayoutID(): Int {
//        return R.layout.activity_returns_good_list
        return R.layout.common_empty_view
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("退货退款列表")

    }


}
