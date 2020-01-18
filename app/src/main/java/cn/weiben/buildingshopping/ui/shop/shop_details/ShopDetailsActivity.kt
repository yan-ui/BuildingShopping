package cn.weiben.buildingshopping.ui.shop.shop_details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.*
import cn.weiben.buildingshopping.ui.adapter.ExpandableItemAdapter
import cn.weiben.buildingshopping.ui.adapter.ExpandableShopGoodsItemAdapter
import cn.weiben.buildingshopping.ui.adapter.HomeCommonGoodsRvAdapter
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity
import cn.weiben.buildingshopping.ui.shop.category.ShopCategoryRvActivity
import cn.weiben.buildingshopping.widget.CustomGoodsParamPopup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.lxj.xpopup.XPopup
import kotlinx.android.synthetic.main.activity_shop_details.*

class ShopDetailsActivity : BaseMVPActivity<ShopDetailsPresenter>(), ShopDetailsContract.View {

    companion object {
        var categoryList: List<CategoriesBean> = ArrayList()
    }

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_shop_details
    }

    private lateinit var suppId: String
    override fun initView(savedInstanceState: Bundle?) {
        suppId = intent.getStringExtra("id")
        mPresenter.getShopDetails(suppId)
    }

    override fun initPresenter(): ShopDetailsPresenter {
        return ShopDetailsPresenter()
    }


    override fun setShopDetails(data: ShopDetailsBean) {
        categoryList = data.categories

        mBestRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val best_adapter = HomeCommonGoodsRvAdapter(data.best_goods)
        best_adapter.bindToRecyclerView(mBestRecyclerView)
        mBestRecyclerView.adapter = best_adapter
        best_adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as HomeBean.NewGoodsBean
            val intent = Intent(this, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            startActivity(intent)
        }


        mNewRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val new_adapter = HomeCommonGoodsRvAdapter(data.new_goods)
        new_adapter.bindToRecyclerView(mNewRecyclerView)
        mNewRecyclerView.adapter = new_adapter
        new_adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.data[position] as HomeBean.NewGoodsBean
            val intent = Intent(this, GoodsDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            startActivity(intent)
        }


        val goods_data = ArrayList<MultiItemEntity>()
        data.category_goods.forEach {
            val bean = GoodsLevelItem(it.cat_name, it.cat_pic_url)
            bean.subItems = it.goods
            goods_data.add(bean)
        }

        val expand_adapter = ExpandableShopGoodsItemAdapter(goods_data)
        val manager = GridLayoutManager(this, 2)
        mGoodsRecyclerView.layoutManager = manager
        mGoodsRecyclerView.adapter = expand_adapter
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (expand_adapter.getItemViewType(position) == ExpandableShopGoodsItemAdapter.TYPE_PERSON) 1 else manager.spanCount
            }
        }

        expand_adapter.expandAll()


        btnAllGoods.setOnClickListener {
            val intent = Intent(mContext, CommonWebviewActivity::class.java)
            intent.putExtra("url", "https://www.chinajcscw.com/mobile/supplier.php?go=search&suppId=${data.suppinfo.supplier_id}&keywords=")
            startActivity(intent)

        }

        btnActivity.setOnClickListener {
            val intent = Intent(mContext, CommonWebviewActivity::class.java)
            intent.putExtra("url", "https://www.chinajcscw.com/mobile/supplier.php?go=activity&suppId=${data.suppinfo.supplier_id}&keywords=")
            startActivity(intent)

        }

        btnCategory.setOnClickListener {
            startActivity(Intent(this, ShopCategoryRvActivity::class.java))
        }

        GlideManager.loadCircleImg(data.shop_logo, ivAvatar)
        tvFensi.text = "${data.guanzhu_num}\n粉丝"
        tvDangerMoney.text = "保证金（${data.supplier_bond}）"
        tvName.text = data.page_title
        if (data.isIs_guanzhu) {
            btnCollect.text = "已收藏"
        } else {
            btnCollect.text = "收藏"
            btnCollect.setOnClickListener {
                mPresenter.collectShop(suppId)
            }
        }

        val paramsList = ArrayList<GoodsDetail.RankPricesBean>()
        val bean1 = GoodsDetail.RankPricesBean()
        bean1.rank_name = "店铺等级"
        bean1.price = data.suppinfo.rank_name
        paramsList.add(bean1)

        val bean2 = GoodsDetail.RankPricesBean()
        bean2.rank_name = "宝贝描述"
        bean2.price = "5.0"
        paramsList.add(bean2)

        val bean3 = GoodsDetail.RankPricesBean()
        bean3.rank_name = "卖家服务"
        bean3.price = "5.0"
        paramsList.add(bean3)

        val bean4 = GoodsDetail.RankPricesBean()
        bean4.rank_name = "物流服务"
        bean4.price = "5.0"
        paramsList.add(bean4)

        val bean5 = GoodsDetail.RankPricesBean()
        bean5.rank_name = "商品数量"
        bean5.price = "350"
        paramsList.add(bean5)

        val bean6 = GoodsDetail.RankPricesBean()
        bean6.rank_name = "开店时间"
        bean6.price = data.suppinfo.add_time
        paramsList.add(bean6)

        val bean7 = GoodsDetail.RankPricesBean()
        bean7.rank_name = "详细地址"
        bean7.price = data.suppinfo.address
        paramsList.add(bean7)

        val bean8 = GoodsDetail.RankPricesBean()
        bean8.rank_name = "所在地"
        bean8.price = "上海上海"
        paramsList.add(bean8)

        val bean9 = GoodsDetail.RankPricesBean()
        bean9.rank_name = "公司介绍"
        bean9.price = data.suppinfo.business_sphere
        paramsList.add(bean9)

        btnDetails.setOnClickListener {
            XPopup.Builder(this)
                    .asCustom(CustomGoodsParamPopup(this, paramsList, data.page_title))
                    .show()
        }


    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getShopDetails(suppId)
    }

}
