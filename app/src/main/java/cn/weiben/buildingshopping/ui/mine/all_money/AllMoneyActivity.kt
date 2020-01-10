package cn.weiben.buildingshopping.ui.mine.all_money

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.MoneyManagerBean
import cn.weiben.buildingshopping.ui.mine.account_detail.AccountDetailActivity
import cn.weiben.buildingshopping.ui.mine.application_list.ApplyListActivity
import cn.weiben.buildingshopping.ui.mine.bonus.BonusActivity
import cn.weiben.buildingshopping.ui.mine.recharge.RechargeActivity
import cn.weiben.buildingshopping.ui.mine.withdraw.WithdrawActivity
import kotlinx.android.synthetic.main.activity_all_money.*
import q.rorbin.badgeview.QBadgeView

class AllMoneyActivity : BaseMVPActivity<AllMoneyPresenter>(), AllMoneyContract.View {

    override fun initPresenter(): AllMoneyPresenter {
        return AllMoneyPresenter()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_all_money
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("资金管理")

        GlideManager.loadImg("https://www.chinajcscw.com/mobile/themesmobile/mo_paleng_moban/images/user/ca1.png", ivHongBao)
        GlideManager.loadImg("https://www.chinajcscw.com/mobile/themesmobile/mo_paleng_moban/images/user/ca2.png", ivRecharge)
        GlideManager.loadImg("https://www.chinajcscw.com/mobile/themesmobile/mo_paleng_moban/images/user/ca4.png", ivWithdraw)
        GlideManager.loadImg("https://www.chinajcscw.com/mobile/themesmobile/mo_paleng_moban/images/user/ca5.png", ivMingxi)
        GlideManager.loadImg("https://www.chinajcscw.com/mobile/themesmobile/mo_paleng_moban/images/user/ca6.png", ivSubmit)

        mPresenter.getUser()


        llHongbao.setOnClickListener {
            startActivity(Intent(this,BonusActivity::class.java))
        }

        llRecharge.setOnClickListener {
            startActivity(Intent(this,RechargeActivity::class.java))
        }

        llWithdraw.setOnClickListener {
            startActivity(Intent(this,WithdrawActivity::class.java))
        }

        llDetail.setOnClickListener {
            startActivity(Intent(this, AccountDetailActivity::class.java))
        }

        llSubmit.setOnClickListener {
            startActivity(Intent(this, ApplyListActivity::class.java))
        }

    }

    override fun setUser(data: MoneyManagerBean?) {
        if (data == null) {
            return
        }

        QBadgeView(this).bindTarget(ivHongBao)
                .badgeNumber = 1 //data.user_info.bonus_count.toInt()

        tvMoney.text = "￥" + data.user_info.user_money
        pay_points.text = "积分:${data.user_info.user_points}"
    }


}
