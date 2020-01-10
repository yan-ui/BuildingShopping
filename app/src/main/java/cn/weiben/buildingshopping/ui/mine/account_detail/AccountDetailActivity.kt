package cn.weiben.buildingshopping.ui.mine.account_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity

class AccountDetailActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_account_detail
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("账户明细")


    }


}
