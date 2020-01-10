package cn.weiben.buildingshopping.ui.mine.withdraw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_recharge.*

class WithdrawActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_withdraw
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("提现")

        btnSubmit.setOnClickListener {
            val money = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if (money.isEmpty()) {
                ToastUtils.showShort("请输入提现金额")
                return@setOnClickListener
            }

            if (content.isEmpty()) {
                ToastUtils.showShort("请输入会员备注")
                return@setOnClickListener
            }


            showLoading()

            Thread {
                Thread.sleep(1000)
                showSuccess("您要申请提现的金额超过了您现有的余额，此操作将不可进行！")

            }.start()


        }

    }


}
