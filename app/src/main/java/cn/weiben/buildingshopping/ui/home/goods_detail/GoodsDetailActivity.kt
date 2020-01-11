package cn.weiben.buildingshopping.ui.home.goods_detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.model.BuyGoods
import cn.weiben.buildingshopping.model.GoodsDetail
import cn.weiben.buildingshopping.ui.adapter.GoodsDetailAdapter
import cn.weiben.buildingshopping.ui.home.goods_detail.test.GoodsDetailContract
import cn.weiben.buildingshopping.ui.home.goods_detail.test.GoodsDetailPresenter
import cn.weiben.buildingshopping.ui.order.OrderPayActivity
import cn.weiben.buildingshopping.utils.BannerGlideImageLoader
import cn.weiben.buildingshopping.utils.DateUtils
import cn.weiben.buildingshopping.utils.HtmlTask
import cn.weiben.buildingshopping.utils.HtmlUtils
import cn.weiben.buildingshopping.widget.CustomGoodsParamPopup
import cn.weiben.buildingshopping.widget.CustomGoodsTypePopup
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.lxj.xpopup.XPopup
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.activity_goods_detail.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GoodsDetailActivity : BaseMVPActivity<GoodsDetailPresenter>(), GoodsDetailContract.View {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_goods_detail
    }


    override fun initPresenter(): GoodsDetailPresenter {
        return GoodsDetailPresenter()
    }

    private val mFragmentContainerHelper = FragmentContainerHelper()
    private var goodsId = ""
    override fun initView(savedInstanceState: Bundle?) {
        goodsId = intent.getStringExtra("id")
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
                    if (adapter == null) {
                        return@setOnClickListener
                    }
                    when (index) {
                        0 -> {
                            mRecyclerView.scrollToPosition(0)
                        }

                        1 -> {
                            mRecyclerView.smoothScrollBy(0, 1600)
                        }

                        2 -> {
                            mRecyclerView.smoothScrollBy(0, 2000)
                        }
                    }

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


        mPresenter.getGoodsDetail(goodsId)
    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getGoodsDetail(goodsId)
    }

    private var adapter: GoodsDetailAdapter? = null
    override fun setGoodsDetail(bean: GoodsDetail) {
        adapter = GoodsDetailAdapter(ArrayList<String>())
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        adapter!!.bindToRecyclerView(mRecyclerView)
        mRecyclerView.adapter = adapter

        initGoods(bean, adapter!!)
        initDetails(bean, adapter!!)
        initCommnet(bean, adapter!!)


        btnCall.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:" + bean.dianpu.servicephone)
            startActivity(intent)
        }


        if (bean.goods.supplier_id != "0") {
            btnAddShopCart.text = "产品展示推广"
            btnAddShopCart.setOnClickListener { }
            btnBuy.visibility = View.GONE
            btnBuy.setOnClickListener { }
        }

        btnCollect.setOnClickListener {
            mPresenter.collectGoods(goodsId)
        }

    }

    private var isBuy = false
    private var timer: CustomCountDown? = null
    private fun initGoods(bean: GoodsDetail, adapter: GoodsDetailAdapter) {
        val view = View.inflate(this, R.layout.item_goods_details_top_view, null)
        val banner = view.findViewById<Banner>(R.id.banner)
        initBanner(banner, bean.pictures)

        val tvGoodsName = view.findViewById<TextView>(R.id.tvGoodsName)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvOldPrice = view.findViewById<TextView>(R.id.tvOldPrice)
        val tvZheKouNum = view.findViewById<TextView>(R.id.tvZheKouNum)
        val tvCommentNum = view.findViewById<TextView>(R.id.tvCommentNum)
        val tvBuyNum = view.findViewById<TextView>(R.id.tvBuyNum)
        val tvPromoteTag = view.findViewById<TextView>(R.id.tvPromoteTag)
        val tvEndTime = view.findViewById<TextView>(R.id.tvEndTime)
        val tvPointMsg = view.findViewById<TextView>(R.id.tvPointMsg)
        val btnGoodsTypeView = view.findViewById<TextView>(R.id.btnGoodsTypeView)
        val btnGoodsParamView = view.findViewById<TextView>(R.id.btnGoodsParamView)
        val btnRankPriceView = view.findViewById<TextView>(R.id.btnRankPriceView)

        tvGoodsName.text = bean.goods_name
        if (bean.goods.is_promote) {
            tvPrice.text = bean.goods.promote_price
            tvPromoteTag.visibility = View.VISIBLE
            tvEndTime.visibility = View.VISIBLE
            tvPointMsg.text = "赠送积分：${bean.goods.promote_price}"

            val left = bean.promote_end_time.toLong() * 1000 - System.currentTimeMillis()
            if (left <= 0) {
                timer = null
                tvEndTime.text = "倒计时结束"
            } else {
                tvEndTime.text = "剩余：" + DateUtils.secToTime((left / 1000 + if (left % 1000 == 0L) 0 else 1).toInt())
                timer = CustomCountDown(left, 1000, tvEndTime)
                timer?.start()
            }

        } else {
            tvPrice.text = bean.goods.shop_price
            tvPromoteTag.visibility = View.GONE
            tvEndTime.visibility = View.GONE
            tvPointMsg.text = "赠送积分：${bean.goods.shop_price}"
        }

        SpanUtils.with(tvOldPrice).append(bean.goods.market_price).setStrikethrough().create()

        tvZheKouNum.text = "折扣：${bean.zhekou}折"
        tvCommentNum.text = "${bean.pinglun}人评论"
        tvBuyNum.text = "${bean.order_num}人已购"

        btnRankPriceView.setOnClickListener {
            XPopup.Builder(this)
                    .asCustom(CustomGoodsParamPopup(this, bean.rank_prices))
                    .show()
        }

        val paramsList = ArrayList<GoodsDetail.RankPricesBean>()
        val bean1 = GoodsDetail.RankPricesBean()
        bean1.rank_name = "商品名称"
        bean1.price = bean.goods_name
        paramsList.add(bean1)

        val bean2 = GoodsDetail.RankPricesBean()
        bean2.rank_name = "商品编号"
        bean2.price = bean.goods.goods_sn
        paramsList.add(bean2)

        val bean3 = GoodsDetail.RankPricesBean()
        bean3.rank_name = "上架时间"
        bean3.price = bean.goods.add_time
        paramsList.add(bean3)

        val bean4 = GoodsDetail.RankPricesBean()
        bean4.rank_name = "商品重量"
        bean4.price = bean.goods.goods_weight
        paramsList.add(bean4)

        val bean5 = GoodsDetail.RankPricesBean()
        bean5.rank_name = "商品库存"
        bean5.price = bean.goods.goods_number + " " + bean.goods.measure_unit
        paramsList.add(bean5)

        btnGoodsParamView.setOnClickListener {
            XPopup.Builder(this)
                    .asCustom(CustomGoodsParamPopup(this, paramsList, 2))
                    .show()
        }

        val goodsTypePopup = CustomGoodsTypePopup(this, bean)
        if (!bean.specification.isNullOrEmpty()) {
            if (!bean.specification[0].values.isNullOrEmpty()) {
                val it = bean.specification[0].values[0]
                tvPrice.text = it.format_price
                tvPointMsg.text = "赠送积分：" + it.result_jf
                btnGoodsTypeView.text = "产品规格显示窗口：规格：" + it.label
            }
        }

        goodsTypePopup.setIOnItemSelectedListener(object : CustomGoodsTypePopup.IOnItemSelectedListener {
            override fun itemSubmit(bea: GoodsDetail.SpecificationBean.ValuesBean, number: Int) {
                if (bean.goods.supplier_id == "0") {
                    val buyGoods = BuyGoods()
                    buyGoods.parent = 0
                    buyGoods.quick = 0
                    buyGoods.goods_id = goodsId
                    buyGoods.number = "" + number
                    val spec = ArrayList<String>()
                    spec.add(bea.id)
                    buyGoods.spec = spec
                    if (isBuy) {
                        val intent = Intent(this@GoodsDetailActivity, OrderPayActivity::class.java)
                        intent.putExtra("is_buy", true)
                        intent.putExtra("goods", Gson().toJson(buyGoods))
                        startActivity(intent)
                    } else {
                        mPresenter.addShopCart(buyGoods)
                    }
                } else {
                    ToastUtils.showShort("产品展示推广")
                }

            }

            override fun itemSelected(bean: GoodsDetail.SpecificationBean.ValuesBean) {
                tvPrice.text = bean.format_price
                tvPointMsg.text = "赠送积分：" + bean.result_jf
                btnGoodsTypeView.text = "产品规格显示窗口：规格：" + bean.label
            }

        })
        val xpopup = XPopup.Builder(this).asCustom(goodsTypePopup)
        btnGoodsTypeView.setOnClickListener {
            isBuy = false
            xpopup.show()
        }

        btnAddShopCart.setOnClickListener {
            isBuy = false
            xpopup.show()
        }

        btnBuy.setOnClickListener {
            isBuy = true
            xpopup.show()
        }

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

    private inner class CustomCountDown(millisInFuture: Long, countDownInterval: Long, private val time: TextView?) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(left: Long) {
            time?.text = "剩余：" + DateUtils.secToTime((left / 1000 + if (left % 1000 == 0L) 0 else 1).toInt())
        }

        override fun onFinish() {
            time?.text = "倒计时结束"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

}
