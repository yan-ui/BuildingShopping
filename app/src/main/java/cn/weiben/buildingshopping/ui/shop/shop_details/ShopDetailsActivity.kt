package cn.weiben.buildingshopping.ui.shop.shop_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.GoodsDetail
import cn.weiben.buildingshopping.model.ShopDetailsBean
import cn.weiben.buildingshopping.widget.CustomGoodsParamPopup
import com.bumptech.glide.Glide
import com.lxj.xpopup.XPopup
import kotlinx.android.synthetic.main.activity_shop_details.*

class ShopDetailsActivity : BaseMVPActivity<ShopDetailsPresenter>(), ShopDetailsContract.View {

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
