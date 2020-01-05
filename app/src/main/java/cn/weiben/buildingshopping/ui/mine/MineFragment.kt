package cn.weiben.buildingshopping.ui.mine

import android.content.Intent
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseFragment
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.UserBean
import cn.weiben.buildingshopping.ui.mine.setting.UserSettingActivity
import cn.weiben.buildingshopping.ui.mine.setting.UserSettingContract
import com.bumptech.glide.annotation.GlideModule
import kotlinx.android.synthetic.main.fragment_mine.*
import q.rorbin.badgeview.QBadgeView

class MineFragment : BaseMVPFragment<MinePresenter>(), MineContract.View {

    override fun initPresenter(): MinePresenter {
        return MinePresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_mine
    }

    override fun lazyData() {

    }

    override fun initView() {
        btnSetting.setOnClickListener {
            startActivity(Intent(mActivity,UserSettingActivity::class.java))
        }

        mPresenter.getUser()
    }

    override fun setUser(data: UserBean) {
        GlideManager.loadCircleImg(data.info.headimg,ivAvatar)
        tvNickName.text = data.info.username

        QBadgeView(mActivity).bindTarget(ivAwaitPay)
                .setGravityOffset(30f, 5f, true)
                .badgeNumber = data.order_count.await_pay
        QBadgeView(mActivity).bindTarget(ivAwaitShip)
                .setGravityOffset(30f, 5f, true)
                .badgeNumber = data.order_count.await_ship
        QBadgeView(mActivity).bindTarget(ivAwaitReceipt)
                .setGravityOffset(30f, 5f, true)
                .badgeNumber = data.order_count.await_receipt
        QBadgeView(mActivity).bindTarget(ivFinished)
                .setGravityOffset(30f, 5f, true)
                .badgeNumber = data.order_count.finished


    }


}
