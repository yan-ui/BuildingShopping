package cn.weiben.buildingshopping.ui.account.login

import android.content.Intent
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.AccountHelper
import cn.weiben.buildingshopping.ui.account.register.RegisterActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVPActivity<LoginPresenter>(), LoginContract.View {

    override fun initPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("会员登录")

        btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val mobile = etAccount.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (mobile.isEmpty() || mobile.length != 11) {
                ToastUtils.showShort("手机号有误，请重新输入")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            mPresenter.login(mobile,password)
        }

    }

    override fun loginSuccess() {
        finish()
    }


}
