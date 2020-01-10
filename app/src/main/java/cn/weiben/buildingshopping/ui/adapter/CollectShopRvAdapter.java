package cn.weiben.buildingshopping.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.CollectGoodsBean;
import cn.weiben.buildingshopping.model.CollectShopBean;


public class CollectShopRvAdapter extends BaseQuickAdapter<CollectShopBean, BaseViewHolder> {

    public CollectShopRvAdapter(@Nullable List<CollectShopBean> data) {
        super(R.layout.item_collect_shop_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CollectShopBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getShop_logo(), helper.getView(R.id.ivAvatar));
        helper.setText(R.id.tvShopName, item.getShop_name());
        helper.addOnClickListener(R.id.btnDelete);
    }

}