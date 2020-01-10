package cn.weiben.buildingshopping.ui.home

import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.fragment.BaseHttpRecyclerMVPFragment
import cn.weiben.buildingshopping.base.interfaces.AdapterCallBack
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.GoodsBean
import cn.weiben.buildingshopping.model.HomeBean
import cn.weiben.buildingshopping.ui.adapter.HomeCommonGoodsRvAdapter
import cn.weiben.buildingshopping.ui.adapter.HomeGoodsGridRvAdapter
import cn.weiben.buildingshopping.ui.adapter.HomePromoteGoodsRvAdapter
import cn.weiben.buildingshopping.ui.adapter.HomeMenuRvAdapter
import cn.weiben.buildingshopping.ui.home.ad_details.CommonWebViewHtmlActivity
import cn.weiben.buildingshopping.ui.home.ad_details.CommonWebViewUrlActivity
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity
import cn.weiben.buildingshopping.ui.search.SearchActivity
import cn.weiben.buildingshopping.utils.BannerGlideImageLoader
import cn.weiben.buildingshopping.widget.ViewFilpers
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseHttpRecyclerMVPFragment<HomePresenter, GoodsBean, BaseViewHolder, HomeGoodsGridRvAdapter>(), HomeContract.View {

    override fun initPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun getFragmentLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun lazyData() {

    }

    private lateinit var homeBean: HomeBean
    private lateinit var headerView: View

    override fun initView() {
        homeTitleBar.setListener { v, action, extra ->
            startActivity(Intent(mActivity, SearchActivity::class.java))
        }

        initSmartRefreshLayout(mSmartRefreshLayout)
        initRecyclerView(mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(mActivity, 2)
        headerView = View.inflate(mActivity, R.layout.item_home_header_view, null)

        mPresenter.getHomePage()
    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getHomePage()
    }


    override fun getListAsync(page: Int) {
        mPresenter.getHomeGoodsList(page, page * 10, 10)
    }

    override fun setHomePage(bean: HomeBean?) {
        if (bean == null) {
            return
        }
        homeBean = bean

        mPresenter.getHomeGoodsList(0, 10, 10)

    }

    override fun setList(list: MutableList<GoodsBean>?) {
        setList(object : AdapterCallBack<HomeGoodsGridRvAdapter> {
            override fun createAdapter(): HomeGoodsGridRvAdapter {
                val adapter = HomeGoodsGridRvAdapter(list)
                initBanner(headerView, homeBean.wap_index_ad)

                initViewFilp(headerView, homeBean.new_articles)

                initMenuRecyclerView(headerView, homeBean.menu_list)

                initPromotionRecyclerView(headerView, homeBean.promotion_goods)

                initNewGoodsRecyclerView(headerView, homeBean.new_goods)

                initHotGoodsRecyclerView(headerView, homeBean.hot_goods)

                initHomeAdView(headerView, homeBean)

                adapter.addHeaderView(headerView)
                return adapter
            }

            override fun refreshAdapter() {
                adapter.setNewData(list)
            }
        })


    }

    private fun initHomeAdView(headerView: View, homeBean: HomeBean) {
        val iv1 = headerView.findViewById<ImageView>(R.id.iv1)
        val iv2 = headerView.findViewById<ImageView>(R.id.iv2)
        val iv3 = headerView.findViewById<ImageView>(R.id.iv3)
        val iv4 = headerView.findViewById<ImageView>(R.id.iv4)
        val iv5 = headerView.findViewById<ImageView>(R.id.iv5)
        val iv6 = headerView.findViewById<ImageView>(R.id.iv6)
        val iv7 = headerView.findViewById<ImageView>(R.id.iv7)
        val iv8 = headerView.findViewById<ImageView>(R.id.iv8)
        val iv9 = headerView.findViewById<ImageView>(R.id.iv9)
        val iv10 = headerView.findViewById<ImageView>(R.id.iv10)
        val iv11 = headerView.findViewById<ImageView>(R.id.iv11)

        GlideManager.loadImgAuto(homeBean.ad1.ad_code, iv1)
        GlideManager.loadImgAuto(homeBean.ad2.ad_code, iv2)
        GlideManager.loadImgAuto(homeBean.ad3.ad_code, iv3)
        GlideManager.loadImgAuto(homeBean.ad4.ad_code, iv4)
        GlideManager.loadImgAuto(homeBean.ad5.ad_code, iv5)
        GlideManager.loadImgAuto(homeBean.ad6.ad_code, iv6)
        GlideManager.loadImgAuto(homeBean.ad7.ad_code, iv7)
        GlideManager.loadImgAuto(homeBean.ad8.ad_code, iv8)
        GlideManager.loadImgAuto(homeBean.ad9.ad_code, iv9)
        GlideManager.loadImgAuto(homeBean.ad10.ad_code, iv10)
        GlideManager.loadImgAuto(homeBean.ad11.ad_code, iv11)

        iv1.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad1.ad_link)
            startActivity(intent)
        }

        iv2.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad2.ad_link)
            startActivity(intent)
        }

        iv3.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad3.ad_link)
            startActivity(intent)
        }

        iv4.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad4.ad_link)
            startActivity(intent)
        }

        iv5.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad5.ad_link)
            startActivity(intent)
        }
        iv6.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad6.ad_link)
            startActivity(intent)
        }

        iv7.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad7.ad_link)
            startActivity(intent)
        }

        iv8.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad8.ad_link)
            startActivity(intent)
        }

        iv9.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad9.ad_link)
            startActivity(intent)
        }

        iv10.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad10.ad_link)
            startActivity(intent)
        }

        iv11.setOnClickListener {
            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", homeBean.ad11.ad_link)
            startActivity(intent)
        }


    }

    override fun setHomeGoodsList(page: Int, list: MutableList<GoodsBean>?) {
        if (list != null) {
            onLoadSucceed(page, list)
        } else {
            onLoadFailed(page, null)
        }
    }


    private fun initHotGoodsRecyclerView(view: View, hotGoods: MutableList<HomeBean.NewGoodsBean>) {
        val hotGoodsRecyclerView = view.findViewById<RecyclerView>(R.id.hotGoodsRecyclerView)
        hotGoodsRecyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
        val adapter = HomeCommonGoodsRvAdapter(hotGoods)
        adapter.bindToRecyclerView(hotGoodsRecyclerView)
        hotGoodsRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as HomeBean.NewGoodsBean
            val intent = Intent(mActivity, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            startActivity(intent)
        }
    }

    private fun initNewGoodsRecyclerView(view: View, newGoods: MutableList<HomeBean.NewGoodsBean>) {
        val newGoodsRecyclerView = view.findViewById<RecyclerView>(R.id.newGoodsRecyclerView)
        newGoodsRecyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
        val adapter = HomeCommonGoodsRvAdapter(newGoods)
        adapter.bindToRecyclerView(newGoodsRecyclerView)
        newGoodsRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as HomeBean.NewGoodsBean
            val intent = Intent(mActivity, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            startActivity(intent)
        }
    }

    private fun initPromotionRecyclerView(view: View, promotionGoods: MutableList<HomeBean.PromotionGoodsBean>) {
        val promotionGoodsRecyclerView = view.findViewById<RecyclerView>(R.id.promotionGoodsRecyclerView)
        promotionGoodsRecyclerView.layoutManager = LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
        val adapter = HomePromoteGoodsRvAdapter(promotionGoods)
        adapter.bindToRecyclerView(promotionGoodsRecyclerView)
        promotionGoodsRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as HomeBean.PromotionGoodsBean
            val intent = Intent(mActivity, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            startActivity(intent)
        }
    }


    private fun initMenuRecyclerView(view: View, menuList: MutableList<HomeBean.MenuListBean>) {
        val menuRecyclerView = view.findViewById<RecyclerView>(R.id.menuRecyclerView)
        menuRecyclerView.layoutManager = GridLayoutManager(mActivity, 5)
        val adapter = HomeMenuRvAdapter(menuList)
        adapter.bindToRecyclerView(menuRecyclerView)
        menuRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            var url = ""
            when (position) {
                0 -> {
                    url = "https://www.chinajcscw.com/mobile/message.php"
                }

                1 -> {
                    url = "https://www.chinajcscw.com/mobile/pro_search.php?encode=YToxOntzOjE4OiJzZWFyY2hfZW5jb2RlX3RpbWUiO2k6MTU3ODY2Mzc2NTt9"
                }

                2 -> {
                    url = "https://www.168hw.cn/index.php"
                }

                3 -> {
                    url = "https://www.chinajcscw.com/mobile/search.php?encode=YToyOntzOjU6ImludHJvIjtzOjM6Im5ldyI7czoxODoic2VhcmNoX2VuY29kZV90aW1lIjtpOjE1Nzg2NjM3ODM7fQ=="
                }

                4 -> {
                    url = "https://www.chinajcscw.com/mobile/supplier_near.php"
                }

                5 -> {
                    url = "https://www.chinajcscw.com/mobile/exchange.php"
                }

                6 -> {
                    url = "https://www.chinajcscw.com/mobile/category.php?u=1801&id=752"
                }

                7 -> {
                    url = "https://www.chinajcscw.com/mobile/stores.php"
                }

                8 -> {
                    url = "https://www.chinajcscw.com/mobile/article_cat.php?id=13"
                }

                9 -> {
                    url = "https://www.chinajcscw.com/mobile/article_cat.php?id=16"
                }

            }

            val intent = Intent(mActivity, CommonWebviewActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)

        }

    }


    private fun initViewFilp(view: View, newArticles: MutableList<HomeBean.NewArticlesBean>) {
        val viewFilpers = view.findViewById<ViewFilpers>(R.id.viewFilpers)
        val mList = ArrayList<String>()

        newArticles.forEach {
            mList.add(it.title)
        }


        viewFilpers.addViewFlipper(mActivity, mList)
        viewFilpers.setInterval(3000)
        viewFilpers.startFlipping()
        viewFilpers.setCallBack(object : ViewFilpers.CallBack {
            override fun itemClick(position: Int) {
                val intent = Intent(mActivity, CommonWebViewHtmlActivity::class.java)
                intent.putExtra("id", newArticles[position].id)
                startActivity(intent)
            }
        })
    }


    private fun initBanner(view: View, wapIndexAd: MutableList<HomeBean.WapIndexAdBean>) {
        val banner = view.findViewById<Banner>(R.id.banner)

        if (wapIndexAd != null) {
            val images = ArrayList<String>()
            val titles = ArrayList<String>()

            wapIndexAd.forEach {
                titles.add(it.name)
                images.add(it.image)
            }

            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            //设置图片加载器
            banner.setImageLoader(BannerGlideImageLoader())
            //设置图片集合
            banner.setImages(images)
            //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage)
            //设置标题集合（当banner样式有显示title时）
            banner.setBannerTitles(titles)
            //设置自动轮播，默认为true
            banner.isAutoPlay(true)
            //设置轮播时间
            banner.setDelayTime(3000)
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.RIGHT)
            banner.setOnBannerListener(object : OnBannerListener {
                override fun OnBannerClick(position: Int) {
                    val intent = Intent(mActivity, CommonWebViewUrlActivity::class.java)
                    intent.putExtra("url", wapIndexAd[position].ad_link)
                    startActivity(intent)
                }
            })
            //banner设置方法全部调用完毕时最后调用
            banner.start()
        }

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        super.onItemClick(adapter, view, position)
        if (adapter == null) {
            return
        }

        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as GoodsBean
            val intent = Intent(mActivity, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            startActivity(intent)
        }

    }


}
