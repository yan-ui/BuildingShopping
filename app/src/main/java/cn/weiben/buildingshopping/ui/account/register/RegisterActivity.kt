package cn.weiben.buildingshopping.ui.account.register

import android.graphics.Color
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.common.Contacts
import cn.weiben.buildingshopping.utils.TimeCount
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterContract.View {

    override fun initPresenter(): RegisterPresenter {
        return RegisterPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_register
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("用户注册")

        Glide.with(this).load(Contacts.DEV_BASE_URL + "mobileapi/captcha.php?uuid=" + DeviceUtils.getMacAddress())
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivCaptcha)

        ivCaptcha.setOnClickListener {
            Glide.with(this).load(Contacts.DEV_BASE_URL + "mobileapi/captcha.php?uuid=" + DeviceUtils.getMacAddress())
                    .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivCaptcha)
        }


        btnGetCode.setOnClickListener {
            val mobile = etMobile.text.toString().trim()
            val captcha = etCaptcha.text.toString().trim()

            if (mobile.isEmpty() || mobile.length != 11) {
                ToastUtils.showShort("手机号有误，请重新输入")
                return@setOnClickListener
            }

            if (captcha.isEmpty()) {
                ToastUtils.showShort("请输入图片验证码")
                return@setOnClickListener
            }

            mPresenter.getSms(mobile, captcha)

        }


        btnRegister.setOnClickListener {
            val mobile = etMobile.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val captcha = etCaptcha.text.toString().trim()
            val code = etCode.text.toString().trim()

            if (mobile.isEmpty() || mobile.length != 11) {
                ToastUtils.showShort("手机号有误，请重新输入")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            if (captcha.isEmpty()) {
                ToastUtils.showShort("请输入图片验证码")
                return@setOnClickListener
            }

            if (code.isEmpty()) {
                ToastUtils.showShort("请输入短信验证码")
                return@setOnClickListener
            }

            if(!cbRead.isChecked){
                ToastUtils.showShort("请勾选我已看过并阅读协议")
                return@setOnClickListener
            }

            mPresenter.register(mobile, password, captcha, code)

        }


    }


    override fun registerSuccess() {
        finish()
    }


    private var timeCount: TimeCount? = null
    override fun getCaptchaSuccess() {
        timeCount = TimeCount(60000, 1000, object : TimeCount.ITimeCountListener {
            override fun onTick(millisUntilFinished: Long) {
                btnGetCode.setTextColor(Color.parseColor("#999999"))
                btnGetCode.isEnabled = false
                btnGetCode.text = "${millisUntilFinished / 1000}秒"
            }

            override fun onFinish() {
                btnGetCode.setTextColor(resources.getColor(R.color.red))
                btnGetCode.isEnabled = true
                btnGetCode.text = "获取验证码"
            }

        })
        timeCount!!.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        timeCount?.cancel()
        timeCount = null
    }

}
