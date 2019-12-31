//package cn.weiben.buildingshopping.ui.home
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import cn.weiben.buildingshopping.R
//import cn.weiben.buildingshopping.base.NullPresenter
//import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
//import cn.weiben.buildingshopping.model.GoodsDetail
//import cn.weiben.buildingshopping.ui.adapter.ItemTitlePagerAdapter
//import cn.weiben.buildingshopping.widget.NoScrollViewPager
//import com.gxz.PagerSlidingTabStrip
//import kotlinx.android.synthetic.main.activity_goods_detail.*
//
//
//class GoodsDetailActivity : BaseMVPActivity<GoodsDetailPresenter>(),GoodsDetailContract.View {
//
//    override fun getActivityLayoutID(): Int {
//        return R.layout.activity_goods_detail
//    }
//
//
//    override fun initPresenter(): GoodsDetailPresenter {
//        return GoodsDetailPresenter()
//    }
//
//    lateinit var vpContent: NoScrollViewPager
//    lateinit var goodsBean:GoodsDetail
//    override fun initView(savedInstanceState: Bundle?) {
//        val centerView = View.inflate(this, R.layout.titlebar_custom_center_layout, null)
//        commonTitleBar.setCenterView(centerView)
//
//        val psts_tabs = centerView.findViewById<PagerSlidingTabStrip>(R.id.psts_tabs)
//
//        val fragmentList: MutableList<Fragment> = ArrayList()
//        fragmentList.add(GoodsInfoFragment())
//        fragmentList.add(GoodsDetailFragment())
//        fragmentList.add(GoodsCommentFragment())
//
//        vpContent = vp_content
//
//        vp_content.adapter = ItemTitlePagerAdapter(supportFragmentManager,
//                fragmentList, arrayOf("商品", "详情", "评价"))
//        vp_content.offscreenPageLimit = 3
//        psts_tabs.setViewPager(vp_content)
//
//        mPresenter.getGoodsDetail("2335")
//    }
//
//    override fun onRetry() {
//        super.onRetry()
//        mPresenter.getGoodsDetail("2335")
//    }
//
//    override fun setGoodsDetail(bean: GoodsDetail) {
//        goodsBean = bean
//    }
//
//
//}
