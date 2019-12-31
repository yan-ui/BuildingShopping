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


public class SortListGoodsGridRvAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    private boolean isGrid = true;

    public SortListGoodsGridRvAdapter(@Nullable List<GoodsBean> data) {
        super(R.layout.item_sort_grid_goods_view, data);
    }

    public void setGridModel(boolean isGrid) {
        this.isGrid = isGrid;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsBean item) {
        if (isGrid) {
            helper.setGone(R.id.llGridLayout, true);
            helper.setGone(R.id.llListLayout, false);

            helper.setVisible(R.id.tvSupplierStatus,item.getSupplier_status().equals("0"));

            GlideManager.INSTANCE.loadImgCenterInside(item.getGoods_thumb(), helper.getView(R.id.ivImage));
            helper.setText(R.id.tvName, item.getName());
            helper.setText(R.id.tvPromotePrice, item.getShop_price());
            TextView tvFinalPrice = helper.getView(R.id.tvFinalPrice);
            SpanUtils.with(tvFinalPrice)
                    .append(item.getMarket_price())
                    .setStrikethrough()
                    .create();
        } else {
            helper.setGone(R.id.llGridLayout, false);
            helper.setGone(R.id.llListLayout, true);

            helper.setVisible(R.id.tvSupplierStatus1,item.getSupplier_status().equals("0"));

            GlideManager.INSTANCE.loadImgCenterInside(item.getGoods_thumb(), helper.getView(R.id.ivImage1));
            helper.setText(R.id.tvName1, item.getName());
            helper.setText(R.id.tvPromotePrice1, item.getShop_price());
            TextView tvFinalPrice = helper.getView(R.id.tvFinalPrice1);
            SpanUtils.with(tvFinalPrice)
                    .append(item.getMarket_price())
                    .setStrikethrough()
                    .create();
        }
    }

}