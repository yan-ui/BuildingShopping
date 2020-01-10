package cn.weiben.buildingshopping.ui.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.BonusBean;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.ShopBean;
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity;


public class BonusBeanRvAdapter extends BaseQuickAdapter<BonusBean.BounsBean, BaseViewHolder> {

    public BonusBeanRvAdapter(@Nullable List<BonusBean.BounsBean> data) {
        super(R.layout.item_bonus_list_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BonusBean.BounsBean item) {
        helper.setText(R.id.tvBonusSn, "红包序号："+item.getBonus_sn())
                .setText(R.id.tvSupplierName,"发行店铺："+ item.getSupplier_name())
                .setText(R.id.tvName, "使用条件："+item.getType_name())
                .setText(R.id.tvEndTime,"有效时间："+ item.getUse_enddate())
                .setText(R.id.tvMoney, "￥" + item.getType_money())
                .setText(R.id.tvStatus, item.getStatus());


    }

}