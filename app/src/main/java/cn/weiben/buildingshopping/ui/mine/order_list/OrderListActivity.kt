package cn.weiben.buildingshopping.ui.mine.order_list

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.ui.adapter.MyFragmentPagerAdapter
import cn.weiben.buildingshopping.ui.test.SecondFragment
import com.blankj.utilcode.util.ConvertUtils
import com.trello.rxlifecycle3.components.support.RxFragment
import kotlinx.android.synthetic.main.activity_order_list.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView

class OrderListActivity : BaseActivity() {
    override fun getActivityLayoutID(): Int {
        return R.layout.activity_order_list
    }

    private var position = 0
    private var mChannels = ArrayList<String>()
    private var mChannelFragments = ArrayList<RxFragment>()
    private lateinit var commonNavigator: CommonNavigator
    private var mChannelPagerAdapter: MyFragmentPagerAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("我的订单")
        position = intent.getIntExtra("position", 0)

        mChannels.add("全部")
        mChannels.add("代付款")
        mChannels.add("待发货")
        mChannels.add("待收货")
        mChannels.add("已完成")


        initMagicIndicator()

        initViewPagerFragment()

    }

    private fun initViewPagerFragment() {
        for ((index, item) in mChannels.withIndex()) {
            val newsFragment = OrderHttpRecyclerFragment()

            when (index) {
                0 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", -1)
                    newsFragment.arguments = bundle
                }

                1 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", 100)
                    newsFragment.arguments = bundle
                }

                2 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", 101)
                    newsFragment.arguments = bundle
                }

                3 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", 105)
                    newsFragment.arguments = bundle
                }

                4 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", 102)
                    newsFragment.arguments = bundle
                }
            }

            mChannelFragments.add(newsFragment)//添加到集合中
        }

        mChannelPagerAdapter = MyFragmentPagerAdapter(fragmentManager, mChannelFragments)
        mViewPager.adapter = mChannelPagerAdapter
        mViewPager.offscreenPageLimit = mChannels.size
        mViewPager.setCurrentItem(position, false)
    }

    private fun initMagicIndicator() {
        commonNavigator = CommonNavigator(this)
        commonNavigator.isSkimOver = true
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return mChannels.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val clipPagerTitleView = ClipPagerTitleView(context)
                clipPagerTitleView.text = mChannels[index]
                clipPagerTitleView.textColor = Color.parseColor("#888888")
                clipPagerTitleView.clipColor = context.resources.getColor(R.color.colorAccent)
                clipPagerTitleView.setOnClickListener { mViewPager.setCurrentItem(index, false) }
                return clipPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineWidth = ConvertUtils.dp2px(20f).toFloat()
                indicator.yOffset = ConvertUtils.dp2px(3f).toFloat()
                indicator.roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
                indicator.startInterpolator = AccelerateInterpolator()
                indicator.endInterpolator = DecelerateInterpolator(2.0f)
                indicator.setColors(context.resources.getColor(R.color.colorAccent))
                return indicator
            }
        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, mViewPager)
    }


}
