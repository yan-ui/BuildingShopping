package cn.weiben.buildingshopping.ui.mine.setting

import android.os.Bundle
import cn.qqtheme.framework.picker.DatePicker
import cn.qqtheme.framework.util.ConvertUtils
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.UserEditBean
import kotlinx.android.synthetic.main.activity_user_setting.*
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*


class UserSettingActivity : BaseMVPActivity<UserSettingPresenter>(), UserSettingContract.View {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_user_setting
    }

    override fun initPresenter(): UserSettingPresenter {
        return UserSettingPresenter()
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("信息修改")
        mPresenter.getUser()
    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getUser()
    }

    override fun setUser(data: UserEditBean) {
        GlideManager.loadCircleImg(data.profile.headimg, ivAvatar)
        ivAvatar.setOnClickListener {

        }


        when (data.profile.sex) {
            "0" -> {
                rgSex.check(R.id.rbSex0)
            }
            "1" -> {
                rgSex.check(R.id.rbSex1)
            }
            "2" -> {
                rgSex.check(R.id.rbSex2)
            }
        }

        etNickName.setText(data.profile.user_name)
        if (data.profile.birthday != "0000-00-00") {
            tvBirthDay.text = data.profile.birthday

        }
        tvBirthDay.setOnClickListener {
            onYearMonthDayPicker(data.profile.birthday)
        }

        etEmail.setText(data.profile.email)

        btnSubmitUserInfo.setOnClickListener {
            val username = etNickName.text.toString().trim()
            val birthday = tvBirthDay.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val sex = when (rgSex.checkedRadioButtonId) {
                R.id.rbSex0 -> 0
                R.id.rbSex1 -> 1
                R.id.rbSex2 -> 2
                else -> 0
            }

            if (username.isEmpty()) {
                ToastUtils.showShort("请输入用户名")
                return@setOnClickListener
            }
            if (birthday.isEmpty()) {
                ToastUtils.showShort("请选择出生日期")
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                ToastUtils.showShort("请输入邮箱")
                return@setOnClickListener
            }

            mPresenter.setUserProfile(data.profile.headimg,username,birthday,sex,email)

        }

        btnSubmitPass.setOnClickListener {
            val oldPass = etOldPass.text.toString().trim()
            val newPass = etNewPass.text.toString().trim()
            val confirmPass = etConfirmPass.text.toString().trim()

            if (oldPass.isEmpty()) {
                ToastUtils.showShort("请输入原密码")
                return@setOnClickListener
            }

            if (newPass.isEmpty()) {
                ToastUtils.showShort("请输入新密码")
                return@setOnClickListener
            }

            if (confirmPass.isEmpty()) {
                ToastUtils.showShort("请输入确认密码")
                return@setOnClickListener
            }

            if(newPass != confirmPass){
                ToastUtils.showShort("两次输入密码不一致")
                return@setOnClickListener
            }

            mPresenter.setUserPassword(oldPass, newPass, confirmPass)

        }

    }


    private fun onYearMonthDayPicker(birthday: String) {
        val datas = birthday.split("-")

        val picker = DatePicker(this)
        picker.setCanceledOnTouchOutside(true)
        picker.setUseWeight(true)
        picker.setTopPadding(ConvertUtils.toPx(this, 10f))
        picker.setRangeEnd(2020, 12, 31)
        picker.setRangeStart(1960, 1, 1)
        picker.setSelectedItem(datas[0].toInt(), datas[1].toInt(), datas[2].toInt())
        picker.setResetWhileWheel(false)
        picker.setOnDatePickListener(object : DatePicker.OnYearMonthDayPickListener {
            override fun onDatePicked(year: String, month: String, day: String) {
                tvBirthDay.text = "$year-$month-$day"
            }
        })
        picker.setOnWheelListener(object : DatePicker.OnWheelListener {
            override fun onYearWheeled(index: Int, year: String) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay())
            }

            override fun onMonthWheeled(index: Int, month: String) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay())
            }

            override fun onDayWheeled(index: Int, day: String) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day)
            }
        })
        picker.show()
    }

}
