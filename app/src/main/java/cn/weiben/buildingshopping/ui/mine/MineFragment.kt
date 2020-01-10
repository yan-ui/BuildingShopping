package cn.weiben.buildingshopping.ui.mine

import android.content.Intent
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseFragment
import cn.weiben.buildingshopping.base.fragment.BaseMVPFragment
import cn.weiben.buildingshopping.manager.AccountHelper
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.UserBean
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity
import cn.weiben.buildingshopping.ui.mine.address_manager.AddressManagerActivity
import cn.weiben.buildingshopping.ui.mine.all_money.AllMoneyActivity
import cn.weiben.buildingshopping.ui.mine.bonus.BonusActivity
import cn.weiben.buildingshopping.ui.mine.collect.MyCollectActivity
import cn.weiben.buildingshopping.ui.mine.comment.CommentActivity
import cn.weiben.buildingshopping.ui.mine.leave_message.LeaveMessageListActivity
import cn.weiben.buildingshopping.ui.mine.order_list.OrderListActivity
import cn.weiben.buildingshopping.ui.mine.recharge.RechargeActivity
import cn.weiben.buildingshopping.ui.mine.returns_list.ReturnsGoodListActivity
import cn.weiben.buildingshopping.ui.mine.setting.UserSettingActivity
import cn.weiben.buildingshopping.ui.mine.setting.UserSettingContract
import cn.weiben.buildingshopping.ui.mine.withdraw.WithdrawActivity
import com.blankj.utilcode.util.ToastUtils
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
        mSmartRefreshLayout.setOnRefreshListener {
            mPresenter.getUser()
        }

        btnSetting.setOnClickListener {
            startActivity(Intent(mActivity, UserSettingActivity::class.java))
        }

        btnAllOrder.setOnClickListener {
            val intent = Intent(mActivity, OrderListActivity::class.java)
            intent.putExtra("position", 0)
            startActivity(intent)
        }

        llAwaitPay.setOnClickListener {
            val intent = Intent(mActivity, OrderListActivity::class.java)
            intent.putExtra("position", 1)
            startActivity(intent)
        }

        btnAddress.setOnClickListener {
            startActivity(Intent(mActivity, AddressManagerActivity::class.java))
        }

        tvAllMoney.setOnClickListener {
            startActivity(Intent(mActivity, AllMoneyActivity::class.java))
        }

        btnRecharge.setOnClickListener {
            startActivity(Intent(mActivity, RechargeActivity::class.java))
        }

        btnWithdraw.setOnClickListener {
            startActivity(Intent(mActivity, WithdrawActivity::class.java))
        }

        llAwaitShip.setOnClickListener {
            val intent = Intent(mActivity, OrderListActivity::class.java)
            intent.putExtra("position", 2)
            startActivity(intent)
        }

        llAwaitReceipt.setOnClickListener {
            val intent = Intent(mActivity, OrderListActivity::class.java)
            intent.putExtra("position", 3)
            startActivity(intent)
        }

        llFinished.setOnClickListener {
            val intent = Intent(mActivity, OrderListActivity::class.java)
            intent.putExtra("position", 4)
            startActivity(intent)

        }

        btnReturns.setOnClickListener {
            startActivity(Intent(mActivity, ReturnsGoodListActivity::class.java))
        }

        btnLeaveMessage.setOnClickListener {
            startActivity(Intent(mActivity, LeaveMessageListActivity::class.java))
        }

        llBonus.setOnClickListener {
            startActivity(Intent(mActivity, BonusActivity::class.java))
        }
        btnComment.setOnClickListener {
            startActivity(Intent(mActivity, CommentActivity::class.java))
        }

        btnCollect.setOnClickListener {
            startActivity(Intent(mActivity, MyCollectActivity::class.java))
        }

        btnShare.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", "https://www.chinajcscw.com/mobile/user.php?act=affiliate")
            startActivity(intent)
        }

        btnInputShop.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", "https://www.chinajcscw.com/mobile/apply.php")
            startActivity(intent)
        }

        llLogout.setOnClickListener {
            AccountHelper.saveToken("")
            clearUserMessage()
            ToastUtils.showShort("退出登录成功")
        }

        mPresenter.getUser()
    }

    override fun setUser(data: UserBean?) {
        mSmartRefreshLayout.finishRefresh()
        if (data == null) {
            return
        }
        GlideManager.loadCircleImg(data.info.headimg, ivAvatar)
        tvNickName.text = data.info.username
        tvRankName.text = data.recomm

        QBadgeView(mActivity).bindTarget(ivAwaitPay)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = data.order_count.await_pay
        QBadgeView(mActivity).bindTarget(ivAwaitShip)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = data.order_count.await_ship
        QBadgeView(mActivity).bindTarget(ivAwaitReceipt)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = data.order_count.await_receipt
        QBadgeView(mActivity).bindTarget(ivFinished)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = data.order_count.finished


    }


    private fun clearUserMessage() {
        GlideManager.loadCircleImg(R.mipmap.touxiang2, ivAvatar)
        tvNickName.text = "未登录"
        tvRankName.text = "普通会员"

        QBadgeView(mActivity).bindTarget(ivAwaitPay)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = 0
        QBadgeView(mActivity).bindTarget(ivAwaitShip)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = 0
        QBadgeView(mActivity).bindTarget(ivAwaitReceipt)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = 0
        QBadgeView(mActivity).bindTarget(ivFinished)
                .setGravityOffset(25f, 2f, true)
                .badgeNumber = 0
    }


}
