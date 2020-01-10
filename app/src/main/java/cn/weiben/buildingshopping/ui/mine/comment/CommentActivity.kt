package cn.weiben.buildingshopping.ui.mine.comment

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
import kotlinx.android.synthetic.main.activity_comment.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView

class CommentActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_comment
    }
    
    
    private val mFragments = ArrayList<RxFragment>()
    private var mTabNameList = ArrayList<String>()
    private lateinit var commonNavigator: CommonNavigator
    private var mChannelPagerAdapter: MyFragmentPagerAdapter? = null
    override fun initView(savedInstanceState: Bundle?) {
        setTitle("我的评价")

        mTabNameList.add("全部")
        mTabNameList.add("待评价")
        mTabNameList.add("已评价")
        mFragments.add(BlankFragment())
        mFragments.add(BlankFragment())
        mFragments.add(BlankFragment())
        initMagicIndicator()
        mChannelPagerAdapter = MyFragmentPagerAdapter(fragmentManager, mFragments)
        mViewPager.adapter = mChannelPagerAdapter
        mViewPager.offscreenPageLimit = mTabNameList.size
        commonNavigator.notifyDataSetChanged()

    }


    private fun initMagicIndicator() {
        commonNavigator = CommonNavigator(this)
        commonNavigator.isSkimOver = true
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return mTabNameList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val clipPagerTitleView = ClipPagerTitleView(context)
                clipPagerTitleView.text = mTabNameList[index]
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
