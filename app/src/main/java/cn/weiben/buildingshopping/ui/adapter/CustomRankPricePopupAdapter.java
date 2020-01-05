package cn.weiben.buildingshopping.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.GoodsDetail;


public class CustomRankPricePopupAdapter extends BaseQuickAdapter<GoodsDetail.RankPricesBean, BaseViewHolder> {

    public CustomRankPricePopupAdapter(List<GoodsDetail.RankPricesBean> data) {
        super(R.layout.item_custom_popup_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetail.RankPricesBean bean) {
        helper.setText(R.id.tvKey,bean.getRank_name()+":");
        helper.setText(R.id.tvValue,bean.getPrice());
    }
}
