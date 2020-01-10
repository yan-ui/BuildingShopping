package cn.weiben.buildingshopping.ui.mine.application_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity

class ApplyListActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
//        return R.layout.activity_apply_list
        return R.layout.common_empty_view
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("申请记录")
    }


}
