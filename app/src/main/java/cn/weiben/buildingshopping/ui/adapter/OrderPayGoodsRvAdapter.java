package cn.weiben.buildingshopping.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.CartPayBean;
import cn.weiben.buildingshopping.model.OrderResultBean;


public class OrderPayGoodsRvAdapter extends BaseQuickAdapter<CartPayBean.CartGoodsBean, BaseViewHolder> {

    public OrderPayGoodsRvAdapter(@Nullable List<CartPayBean.CartGoodsBean> data) {
        super(R.layout.item_order_child_goods_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CartPayBean.CartGoodsBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getGoods_thumb(), helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_name, item.getGoods_name());
        helper.setText(R.id.tv_spec_value, item.getGoods_attr());
        helper.setText(R.id.tv_buy_number, "x" + item.getGoods_number());
        helper.setText(R.id.tv_price_value,  item.getGoods_price());

    }

}