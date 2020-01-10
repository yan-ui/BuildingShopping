package cn.weiben.buildingshopping.ui.mine.setting

import android.content.Intent
import android.os.Bundle
import cn.qqtheme.framework.picker.DatePicker
import cn.qqtheme.framework.util.ConvertUtils
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.UserEditBean
import kotlinx.android.synthetic.main.activity_user_setting.*
import com.blankj.utilcode.util.ToastUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import java.util.ArrayList


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
            selectPic()
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

            val file:File? = if(selectFileList.isNotEmpty()){
                File(selectFileList[0].path)
            }else{
                null
            }

            mPresenter.setUserProfile(selectFileList,username,birthday,sex,email)

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

    private var selectFileList: List<LocalMedia> = ArrayList<LocalMedia>()
    private fun selectPic() {
        PictureSelector.create(mContext)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                // 最大图片选择数量
                .maxSelectNum(1)
                // 最小选择数量
                .minSelectNum(1)
                // 每行显示个数
                .imageSpanCount(4)
                // 多选 or 单选
                .selectionMode(PictureConfig.SINGLE)
                // 是否可预览图片
                .previewImage(true)
                // 是否可播放音频
                .enablePreviewAudio(false)
                // 是否显示拍照按钮
                .isCamera(true)
                // 图片列表点击 缩放效果 默认true
                .isZoomAnim(true)
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                // 是否裁剪
                .enableCrop(true)
                // 是否压缩
                .compress(true)
                //同步true或异步false 压缩 默认同步
                .synOrAsy(true)
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                // glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .glideOverride(160, 160)
                // 是否显示uCrop工具栏，默认不显示
                .hideBottomControls(false)
                // 是否显示gif图片
                .isGif(false)
                // 裁剪框是否可拖拽
                .freeStyleCropEnabled(false)
                // 是否传入已选图片
                .selectionMedia(selectFileList)
                // 小于100kb的图片不压缩
                .minimumCompressSize(100)
                //结果回调onActivityResult code
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    selectFileList = PictureSelector.obtainMultipleResult(data)
                    GlideManager.loadCircleImg(selectFileList[0].path, ivAvatar)
                }
                else -> {
                }
            }
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
