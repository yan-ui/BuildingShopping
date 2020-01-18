package cn.weiben.buildingshopping.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.CategoriesBean;
import cn.weiben.buildingshopping.model.GoodsLevelItem;
import cn.weiben.buildingshopping.model.Level1Item;
import cn.weiben.buildingshopping.model.ShopDetailsBean;
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity;
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity;

public class ExpandableShopGoodsItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_1 = 0;
    public static final int TYPE_PERSON = 1;


    public ExpandableShopGoodsItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_1, R.layout.item_shop_details_group_view);
        addItemType(TYPE_PERSON, R.layout.item_shop_details_child_view);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_1:
                GoodsLevelItem lv0 = (GoodsLevelItem) item;
                holder.setText(R.id.tv_title, lv0.title);
                //set view content
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        int pos = holder.getAdapterPosition();
//                        if (lv0.isExpanded()) {
//                            collapse(pos);
//                        } else {
//                            expand(pos);
//                        }

                        Intent intent = new Intent(mContext, CommonWebviewActivity.class);
                        intent.putExtra("url", "https://www.chinajcscw.com/mobile/" + lv0.url);
                        mContext.startActivity(intent);

                    }
                });
                break;
            case TYPE_PERSON:
                ShopDetailsBean.CategoryGoodsBean.GoodsBean bean = (ShopDetailsBean.CategoryGoodsBean.GoodsBean) item;
                GlideManager.INSTANCE.loadImgCenterInside(bean.getThumb(), holder.getView(R.id.ivImage));
                holder.setText(R.id.tvName, bean.getName());
                holder.setText(R.id.tvPromotePrice, bean.getShop_price());
                TextView tvFinalPrice = holder.getView(R.id.tvFinalPrice);
                SpanUtils.with(tvFinalPrice)
                        .append(bean.getMarket_price())
                        .setStrikethrough()
                        .create();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("id", bean.getId());
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }
}