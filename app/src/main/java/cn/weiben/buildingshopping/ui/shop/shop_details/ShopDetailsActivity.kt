package cn.weiben.buildingshopping.ui.shop.shop_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.NullPresenter
import cn.weiben.buildingshopping.base.activity.BaseMVPActivity
import cn.weiben.buildingshopping.manager.GlideManager
import cn.weiben.buildingshopping.model.ShopDetailsBean
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_shop_details.*

class ShopDetailsActivity : BaseMVPActivity<ShopDetailsPresenter>(),ShopDetailsContract.View {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_shop_details
    }

    private lateinit var suppId:String
    override fun initView(savedInstanceState: Bundle?) {
        suppId = intent.getStringExtra("id")
        mPresenter.getShopDetails(suppId)
    }

    override fun initPresenter(): ShopDetailsPresenter {
        return ShopDetailsPresenter()
    }


    override fun setShopDetails(data: ShopDetailsBean) {

        tvDangerMoney.text = "保证金（${data.supplier_bond}）"
        tvName.text = data.page_title
        if(data.isIs_guanzhu){
            btnCollect.text ="已收藏"
        }else{
            btnCollect.text ="收藏"
            btnCollect.setOnClickListener{
                mPresenter.collectShop(suppId)
            }
        }

    }

    override fun onRetry() {
        super.onRetry()
        mPresenter.getShopDetails(suppId)
    }

}
