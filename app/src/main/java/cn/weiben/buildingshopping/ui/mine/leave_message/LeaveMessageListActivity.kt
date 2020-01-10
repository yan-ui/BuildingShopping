package cn.weiben.buildingshopping.ui.mine.leave_message

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseHttpRecyclerMVPActivity
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.model.LeaveMessageBean
import cn.weiben.buildingshopping.ui.adapter.LeaveMessageAdapter
import cn.weiben.buildingshopping.ui.mine.leave_message.add_message.AddMessageActivity
import cn.weiben.buildingshopping.utils.RecycleViewDivider
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_leave_message_list.*

class LeaveMessageListActivity : BaseHttpRecyclerMVPActivity<LeaveMessageListPresenter, LeaveMessageBean.MessageListBean, BaseViewHolder, LeaveMessageAdapter>(),LeaveMessageListContract.View {

    override fun initPresenter(): LeaveMessageListPresenter {
        return LeaveMessageListPresenter()
    }


    override fun getActivityLayoutID(): Int {
        return R.layout.activity_leave_message_list
    }


    override fun initView(savedInstanceState: Bundle?) {
        setTitle("我的留言")
        setPositiveText("发布")
        setPositiveOnClickListener {
            startActivity(Intent(this, AddMessageActivity::class.java))
        }


        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)

        mRecyclerView.addItemDecoration(RecycleViewDivider(this, LinearLayout.VERTICAL, ConvertUtils.dp2px(10f),
                resources.getColor(R.color.background_color_gray)))

    }


    override fun onResume() {
        super.onResume()
        mSmartRefreshLayout.autoRefresh()
    }

    override fun getListAsync(page: Int) {
        mPresenter.getLeaveMessageList(page)
    }

    override fun setData(page: Int, data: MutableList<LeaveMessageBean.MessageListBean>?) {
        if (data != null) {
            onLoadSucceed(page, data)
        } else {
            onLoadFailed(page, null)
        }
    }


    override fun setList(list: MutableList<LeaveMessageBean.MessageListBean>?) {
        setList(object : AdapterCallBack<LeaveMessageAdapter> {

            override fun createAdapter(): LeaveMessageAdapter {
                return LeaveMessageAdapter(list)
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })
    }


}
