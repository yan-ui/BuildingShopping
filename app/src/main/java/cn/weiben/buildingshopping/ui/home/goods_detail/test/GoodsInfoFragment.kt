package cn.weiben.buildingshopping.ui.home.goods_detail.test

import android.graphics.Paint
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import java.math.BigDecimal
import java.util.ArrayList

import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseFragment
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity
import cn.weiben.buildingshopping.widget.SlideDetailsLayout
import kotlinx.android.synthetic.main.fragment_goods_info.*
import kotlinx.android.synthetic.main.include_item_tab.*

/**
 * item页ViewPager里的商品Fragment
 */
class GoodsInfoFragment : BaseFragment(), View.OnClickListener, SlideDetailsLayout.OnSlideDetailsListener {


    /**
     * 当前商品详情数据页的索引分别是图文详情、规格参数
     */
    private var nowIndex: Int = 0
    private var fromX: Float = 0.toFloat()
    var goodsConfigFragment: GoodsConfigFragment? = null
    var goodsInfoWebFragment: GoodsInfoWebFragment? = null
    private var nowFragment: Fragment? = null
    private var tabTextList: MutableList<TextView>? = null
    private var fragmentList: MutableList<Fragment> = ArrayList()
    private var fragmentTransaction: FragmentTransaction? = null

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_goods_info
    }

    override fun initView() {
        setDetailData()
        setLoopView()
        setRecommendGoods()

        //设置文字中间一条横线
        tv_old_price!!.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        fab_up_slide!!.hide()

        initListener()
        initData()
    }

    private fun initListener() {
        fab_up_slide!!.setOnClickListener(this)
        ll_current_goods!!.setOnClickListener(this)
        ll_activity!!.setOnClickListener(this)
        ll_comment!!.setOnClickListener(this)
        ll_pull_up!!.setOnClickListener(this)
        ll_goods_detail!!.setOnClickListener(this)
        ll_goods_config!!.setOnClickListener(this)
        sv_switch!!.setOnSlideDetailsListener(this)
    }


    private fun initData() {
        fragmentList = ArrayList()
        tabTextList = ArrayList()
        tabTextList!!.add(tv_goods_detail)
        tabTextList!!.add(tv_goods_config)
    }

    /**
     * 加载完商品详情执行
     */
    fun setDetailData() {
        goodsConfigFragment = GoodsConfigFragment()
        goodsInfoWebFragment = GoodsInfoWebFragment()
        fragmentList.add(goodsConfigFragment!!)
        fragmentList.add(goodsInfoWebFragment!!)

        nowFragment = goodsInfoWebFragment
        //默认显示商品详情tab
        childFragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment!!).commitAllowingStateLoss()
    }

    /**
     * 设置推荐商品
     */
    fun setRecommendGoods() {
        val data = ArrayList<RecommendGoodsBean>()
        data.add(RecommendGoodsBean("Letv/乐视 LETV体感-超级枪王 乐视TV超级电视产品玩具 体感游戏枪 电玩道具 黑色",
                "http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg", BigDecimal(599), "799"))
        data.add(RecommendGoodsBean("IPEGA/艾派格 幽灵之子 无线蓝牙游戏枪 游戏体感枪 苹果安卓智能游戏手柄 标配",
                "http://img2.hqbcdn.com/product/00/76/0076cedb0a7d728ec1c8ec149cff0d16.jpg", BigDecimal(299), "399"))
        data.add(RecommendGoodsBean("Letv/乐视 LETV体感-超级枪王 乐视TV超级电视产品玩具 体感游戏枪 电玩道具 黑色",
                "http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg", BigDecimal(599), "799"))
        data.add(RecommendGoodsBean("IPEGA/艾派格 幽灵之子 无线蓝牙游戏枪 游戏体感枪 苹果安卓智能游戏手柄 标配",
                "http://img2.hqbcdn.com/product/00/76/0076cedb0a7d728ec1c8ec149cff0d16.jpg", BigDecimal(299), "399"))
        val handledData = handleRecommendGoods(data)
        //设置如果只有一组数据时不能滑动
        //        vp_recommend.setManualPageable(handledData.size() == 1 ? false : true);
        //        vp_recommend.setCanLoop(handledData.size() == 1 ? false : true);
        //        vp_recommend.setPages(new CBViewHolderCreator() {
        //            @Override
        //            public Object createHolder() {
        //                return new ItemRecommendAdapter();
        //            }
        //        }, handledData);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_pull_up ->
                //上拉查看图文详情
                sv_switch!!.smoothOpen(true)

            R.id.fab_up_slide -> {
                //点击滑动到顶部
                sv_goods_info!!.smoothScrollTo(0, 0)
                sv_switch!!.smoothClose(true)
            }

            R.id.ll_goods_detail -> {
                //商品详情tab
                nowIndex = 0
                scrollCursor()
                switchFragment(nowFragment, goodsInfoWebFragment!!)
                nowFragment = goodsInfoWebFragment
            }

            R.id.ll_goods_config -> {
                //规格参数tab
                nowIndex = 1
                scrollCursor()
                switchFragment(nowFragment, goodsConfigFragment!!)
                nowFragment = goodsConfigFragment
            }

            else -> {
            }
        }
    }

    /**
     * 给商品轮播图设置图片路径
     */
    fun setLoopView() {
        val imgUrls = ArrayList<String>()
        imgUrls.add("http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg")
        imgUrls.add("http://img14.hqbcdn.com/product/77/6c/776c63e6098f05fdc5639adc96d8d6ea.jpg")
        imgUrls.add("http://img13.hqbcdn.com/product/41/ca/41cad5139371e4eb1ce095e5f6224f4d.jpg")
        imgUrls.add("http://img10.hqbcdn.com/product/fa/ab/faab98caca326949b87b770c8080e6cf.jpg")
        imgUrls.add("http://img2.hqbcdn.com/product/6b/b8/6bb86086397a8cd0525c449f29abfaff.jpg")
        //初始化商品图片轮播
        //        vp_item_goods_img.setPages(new CBViewHolderCreator() {
        //            @Override
        //            public Object createHolder() {
        //                return new NetworkImageHolderView();
        //            }
        //        }, imgUrls);
    }

    override fun onStatucChanged(status: SlideDetailsLayout.Status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            //当前为图文详情页
            fab_up_slide!!.show()
//            (mActivity as GoodsDetailActivity).vpContent.setNoScroll(true)

        } else {
            //当前为商品详情页
            fab_up_slide!!.hide()
//            (mActivity as GoodsDetailActivity).vpContent.setNoScroll(false)
        }
    }

    /**
     * 滑动游标
     */
    private fun scrollCursor() {
        val anim = TranslateAnimation(fromX, (nowIndex * v_tab_cursor!!.width).toFloat(), 0f, 0f)
        anim.fillAfter = true//设置动画结束时停在动画结束的位置
        anim.duration = 50
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = (nowIndex * v_tab_cursor.width).toFloat()
        v_tab_cursor.startAnimation(anim)

        //设置Tab切换颜色
        for (i in tabTextList!!.indices) {
            tabTextList!![i].setTextColor(if (i == nowIndex) resources.getColor(R.color.red) else resources.getColor(R.color.black))
        }
    }

    /**
     * 切换Fragment
     *
     * (hide、show、add)
     *
     * @param fromFragment
     * @param toFragment
     */
    private fun switchFragment(fromFragment: Fragment?, toFragment: Fragment) {
        if (nowFragment !== toFragment) {
            fragmentTransaction = fragmentManager!!.beginTransaction()
            if (!toFragment.isAdded) {    // 先判断是否被add过
                fragmentTransaction!!.hide(fromFragment!!).add(R.id.fl_content, toFragment).commitAllowingStateLoss() // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction!!.hide(fromFragment!!).show(toFragment).commitAllowingStateLoss() // 隐藏当前的fragment，显示下一个
            }
        }
    }

    override fun lazyData() {

    }

    companion object {

        /**
         * 处理推荐商品数据(每两个分为一组)
         *
         * @param data
         * @return
         */
        fun handleRecommendGoods(data: List<RecommendGoodsBean>): List<List<RecommendGoodsBean>> {
            val handleData = ArrayList<List<RecommendGoodsBean>>()
            var length = data.size / 2
            if (data.size % 2 != 0) {
                length = data.size / 2 + 1
            }
            for (i in 0 until length) {
                val recommendGoods = ArrayList<RecommendGoodsBean>()
                var temp = 0
                for (j in 0 until if (i * 2 + temp== data.size) 1 else 2) {
                    temp = j
                    recommendGoods.add(data[i * 2 + j])
                }
                handleData.add(recommendGoods)
            }
            return handleData
        }
    }
}