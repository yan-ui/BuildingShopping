package cn.weiben.buildingshopping.ui.home.goods_detail

import android.os.Bundle
import android.view.View
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.model.GoodsDetail
import kotlinx.android.synthetic.main.activity_goods_detail.*
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.weiben.buildingshopping.ui.adapter.GoodsDetailAdapter
import cn.weiben.buildingshopping.ui.home.goods_detail.test.GoodsDetailContract
import cn.weiben.buildingshopping.ui.home.goods_detail.test.GoodsDetailPresenter
import cn.weiben.buildingshopping.utils.BannerGlideImageLoader
import cn.weiben.buildingshopping.utils.HtmlTask
import cn.weiben.buildingshopping.utils.HtmlUtils
import cn.weiben.buildingshopping.widget.ItemWebView
import com.blankj.utilcode.util.ToastUtils
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import net.lucode.hackware.magicindicator.FragmentContainerHelper


class GoodsDetailActivity : BaseMVPActivity<GoodsDetailPresenter>(), GoodsDetailContract.View {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_goods_detail
    }


    override fun initPresenter(): GoodsDetailPresenter {
        return GoodsDetailPresenter()
    }

    private val mFragmentContainerHelper = FragmentContainerHelper()
    override fun initView(savedInstanceState: Bundle?) {
        val centerView = View.inflate(this, R.layout.titlebar_custom_center_layout, null)
        commonTitleBar.setCenterView(centerView)


        val magicIndicator = centerView.findViewById<MagicIndicator>(R.id.mMagicIndicator)
        mFragmentContainerHelper.handlePageSelected(0, false)
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return 3
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val mDataList = arrayOf("商品", "详情", "评价")
                val simplePagerTitleView = ColorTransitionPagerTitleView(context)
                simplePagerTitleView.text = mDataList[index]
                simplePagerTitleView.textSize = 16F
                simplePagerTitleView.normalColor = Color.parseColor("#333333")
                simplePagerTitleView.selectedColor = Color.parseColor("#ff0000")
                simplePagerTitleView.setOnClickListener {
                    mFragmentContainerHelper.handlePageSelected(index)
                    ToastUtils.showShort("测试")
                }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.setColors(Color.parseColor("#ff0000"))
                return indicator
            }
        }
        magicIndicator.navigator = commonNavigator
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator)


        mPresenter.getGoodsDetail("2335")
    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getGoodsDetail("2335")
    }

    override fun setGoodsDetail(bean: GoodsDetail) {
        val adapter = GoodsDetailAdapter(ArrayList<String>())
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        adapter.bindToRecyclerView(mRecyclerView)
        mRecyclerView.adapter = adapter

        initGoods(bean, adapter)
        initDetails(bean, adapter)
        initCommnet(bean, adapter)

    }


    private fun initGoods(bean: GoodsDetail, adapter: GoodsDetailAdapter) {
        val view = View.inflate(this, R.layout.item_goods_details_top_view, null)
        val banner = view.findViewById<Banner>(R.id.banner)
        initBanner(banner, bean.pictures)

        val tvGoodsName = view.findViewById<TextView>(R.id.tvGoodsName)
        tvGoodsName.text = bean.goods_name

        adapter.addHeaderView(view)
    }

    private fun initDetails(bean: GoodsDetail, adapter: GoodsDetailAdapter) {
        val view = View.inflate(this, R.layout.item_goods_details_center_view, null)
        val mText = view.findViewById<TextView>(R.id.mText)
        val htmlTask = HtmlTask(mText)
        htmlTask.execute(HtmlUtils.imageFillWidth(bean.goods.goods_desc))

        adapter.addHeaderView(view)
    }

    private fun initCommnet(bean: GoodsDetail, adapter: GoodsDetailAdapter) {


    }


    private fun initBanner(banner: Banner, datas: MutableList<GoodsDetail.PicturesBean>?) {

        if (datas != null) {
            val images = ArrayList<String>()

            datas.forEach {
                images.add(it.img_url)
            }

            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            //设置图片加载器
            banner.setImageLoader(BannerGlideImageLoader())
            //设置图片集合
            banner.setImages(images)
            //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage)
            //设置标题集合（当banner样式有显示title时）
//            banner.setBannerTitles(titles)
            //设置自动轮播，默认为true
            banner.isAutoPlay(true)
            //设置轮播时间
            banner.setDelayTime(3000)
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.RIGHT)
            banner.setOnBannerListener(object : OnBannerListener {
                override fun OnBannerClick(position: Int) {

                }
            })
            //banner设置方法全部调用完毕时最后调用
            banner.start()
        }

    }

}
