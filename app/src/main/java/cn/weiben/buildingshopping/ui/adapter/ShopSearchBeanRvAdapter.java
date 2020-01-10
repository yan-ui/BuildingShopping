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
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.ShopBean;
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity;


public class ShopSearchBeanRvAdapter extends BaseQuickAdapter<ShopBean.SupplierListBean, BaseViewHolder> {

    public ShopSearchBeanRvAdapter(@Nullable List<ShopBean.SupplierListBean> data) {
        super(R.layout.item_shop_search_list_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopBean.SupplierListBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getShop_logo(), helper.getView(R.id.ivAvatar));
        helper.setText(R.id.tvShopName, item.getShop_name())
                .setText(R.id.tvTotalGoods, "共"+item.getGoods_number()+"件宝贝")
                .setText(R.id.tv_c_rank,item.getC_rank())
                .setText(R.id.tv_serv_rank,item.getServ_rank())
                .setText(R.id.tv_shipp_rank,item.getShipp_rank())
                .setText(R.id.tv_c_rank_text,item.getC_rank_text())
                .setText(R.id.tv_serv_rank_text,item.getServ_rank_text())
                .setText(R.id.tv_shipp_rank_text,item.getShipp_rank_text());

        RecyclerView recyclerView = helper.getView(R.id.goodsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        ShopChildGoodsRvAdapter adapter = new ShopChildGoodsRvAdapter(item.getGoods_list());
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsBean bean = (GoodsBean) adapter.getData().get(position);
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("id",bean.getGoods_id());
                mContext.startActivity(intent);
            }
        });

        helper.addOnClickListener(R.id.btnCollect);


    }

}