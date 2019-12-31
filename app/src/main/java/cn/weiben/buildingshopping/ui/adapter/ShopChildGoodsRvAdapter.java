package cn.weiben.buildingshopping.ui.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.HomeBean;


public class ShopChildGoodsRvAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    public ShopChildGoodsRvAdapter(@Nullable List<GoodsBean> data) {
        super(R.layout.item_shop_child_goods_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getGoods_thumb(), helper.getView(R.id.ivImage));
        helper.setText(R.id.tvName, item.getGoods_name());
        helper.setText(R.id.tvPromotePrice, item.getShop_price());
    }

}