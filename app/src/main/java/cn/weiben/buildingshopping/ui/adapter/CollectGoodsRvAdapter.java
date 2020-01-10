package cn.weiben.buildingshopping.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.CollectGoodsBean;
import cn.weiben.buildingshopping.model.OrderResultBean;


public class CollectGoodsRvAdapter extends BaseQuickAdapter<CollectGoodsBean, BaseViewHolder> {

    public CollectGoodsRvAdapter(@Nullable List<CollectGoodsBean> data) {
        super(R.layout.item_collect_goods_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CollectGoodsBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getGoods_thumb(), helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_name, item.getGoods_name());
        helper.setText(R.id.tv_price_value,  item.getShop_price());

        helper.addOnClickListener(R.id.tv_delete);

    }

}