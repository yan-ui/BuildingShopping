package cn.weiben.buildingshopping.ui.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.GoodsDetail;

public class SuspiciousGoodsTypeAdapter extends BaseQuickAdapter<GoodsDetail.SpecificationBean, BaseViewHolder> {

    private int clickPosition = -1;

    //进入单选页面执行这个方法，选中传过来的ID对应的item
    public void setClickPosition(int position) {
        this.clickPosition = position;
        notifyItemChanged(position);
    }

    public SuspiciousGoodsTypeAdapter(@Nullable List<GoodsDetail.SpecificationBean> data) {
        super(R.layout.item_radiubutton_goods_type_view, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetail.SpecificationBean item) {
        if (helper.getLayoutPosition() == clickPosition) {
            helper.setText(R.id.tv_name, item.getName());
            helper.setTextColor(R.id.tv_name, Color.WHITE);
            helper.setBackgroundRes(R.id.rl_bg, R.drawable.shape_color_red_radius_5_bg);
        } else {
            helper.setTextColor(R.id.tv_name, Color.parseColor("#FF333333"));
            helper.setBackgroundRes(R.id.rl_bg, R.drawable.shape_stroke_gray_color_white_radius_5_bg);
        }

    }


}