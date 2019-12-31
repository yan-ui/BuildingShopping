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


public class HomeGoodsGridRvAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {


    public HomeGoodsGridRvAdapter(@Nullable List<GoodsBean> data) {
        super(R.layout.item_home_best_goods_view, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsBean item) {
            GlideManager.INSTANCE.loadImgCenterInside(item.getThumb(), helper.getView(R.id.ivImage));
            helper.setText(R.id.tvName, item.getName());
            helper.setText(R.id.tvPromotePrice, item.getShop_price());
            TextView tvFinalPrice = helper.getView(R.id.tvFinalPrice);
            SpanUtils.with(tvFinalPrice)
                    .append(item.getMarket_price())
                    .setStrikethrough()
                    .create();
    }

}