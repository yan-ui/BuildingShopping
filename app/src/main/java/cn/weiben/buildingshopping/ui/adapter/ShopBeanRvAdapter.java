package cn.weiben.buildingshopping.ui.adapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.HomeBean;
import cn.weiben.buildingshopping.model.ShopBean;


public class ShopBeanRvAdapter extends BaseQuickAdapter<ShopBean.SupplierListBean, BaseViewHolder> {

    public ShopBeanRvAdapter(@Nullable List<ShopBean.SupplierListBean> data) {
        super(R.layout.item_shop_list_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopBean.SupplierListBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getShop_logo(), helper.getView(R.id.ivAvatar));
        helper.setText(R.id.tvShopName, item.getShop_name())
                .setText(R.id.tvShopAddress, item.getShop_country() + "-" + item.getShop_province() + "-" + item.getShop_city() + "-" + item.getShop_address());

        RecyclerView recyclerView = helper.getView(R.id.goodsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        ShopChildGoodsRvAdapter adapter = new ShopChildGoodsRvAdapter(item.getGoods_list());
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);



    }

}