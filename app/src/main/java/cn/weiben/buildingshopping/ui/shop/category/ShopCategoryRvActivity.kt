package cn.weiben.buildingshopping.ui.shop.category

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import cn.weiben.buildingshopping.R
import cn.weiben.buildingshopping.base.activity.BaseActivity
import cn.weiben.buildingshopping.model.Level1Item
import cn.weiben.buildingshopping.ui.adapter.ExpandableItemAdapter
import cn.weiben.buildingshopping.ui.shop.shop_details.ShopDetailsActivity
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.android.synthetic.main.activity_shop_category_rv.*


class ShopCategoryRvActivity : BaseActivity() {

    override fun getActivityLayoutID(): Int {
        return R.layout.activity_shop_category_rv
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("商品分类")

        val datas = ArrayList<MultiItemEntity>()
        ShopDetailsActivity.categoryList.forEach {
            val bean = Level1Item(it.name,it.url)
            bean.subItems = it.cat_id
            datas.add(bean)
        }

        val adapter = ExpandableItemAdapter(datas)
        val manager = GridLayoutManager(this, 2)
        mRecyclerView.layoutManager = manager
        mRecyclerView.adapter = adapter
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_PERSON) 1 else manager.spanCount
            }
        }

        adapter.expandAll()

    }


}
