package cn.weiben.buildingshopping.ui.mine.leave_message.add_message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_add_message.*

class AddMessageActivity : BaseMVPActivity<AddMessagePresenter>(),AddMessageContract.View {
    override fun initPresenter(): AddMessagePresenter {
        return AddMessagePresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_add_message
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("发布留言")

        btnSubmit.setOnClickListener {
            val type = when(rgGroup.checkedRadioButtonId){
                R.id.rbType1 -> 0
                R.id.rbType2 -> 1
                R.id.rbType3 -> 2
                R.id.rbType4 -> 3
                R.id.rbType5 -> 4
                else -> 0
            }

            val title = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if(title.isEmpty()){
                ToastUtils.showShort("请输入主题")
                return@setOnClickListener
            }

            if(content.isEmpty()){
                ToastUtils.showShort("请输入内容")
                return@setOnClickListener
            }

            mPresenter.addMessage(type,title,content)

        }

    }


}
