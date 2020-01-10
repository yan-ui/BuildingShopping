package cn.weiben.buildingshopping.ui.mine.recharge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_recharge.*

class RechargeActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_recharge
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("在线充值")

        btnSubmit.setOnClickListener {
            val money = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if (money.isEmpty()) {
                ToastUtils.showShort("请输入充值金额")
                return@setOnClickListener
            }

            if (content.isEmpty()) {
                ToastUtils.showShort("请输入会员备注")
                return@setOnClickListener
            }

            if (!rbType1.isChecked) {
                ToastUtils.showShort("请选择支付方式")
                return@setOnClickListener
            }

            showLoading()

            Thread {
                Thread.sleep(1000)
                showSuccess("")
                val intent = Intent(this, CommonWebviewActivity::class.java)
                intent.putExtra("url", "https://www.chinajcscw.com/mobile/pay/alipayapi.php?out_trade_no=799&total_fee=300")
                startActivity(intent)
            }.start()


        }


    }


}
