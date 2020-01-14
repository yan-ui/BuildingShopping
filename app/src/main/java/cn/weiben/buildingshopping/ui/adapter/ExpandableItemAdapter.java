package cn.weiben.buildingshopping.ui.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.CategoriesBean;
import cn.weiben.buildingshopping.model.Level1Item;
import cn.weiben.buildingshopping.ui.main.CommonWebviewActivity;
import cn.weiben.buildingshopping.ui.mine.comment.CommentActivity;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_1 = 0;
    public static final int TYPE_PERSON = 1;


    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_1, R.layout.item_shop_category_group_view);
        addItemType(TYPE_PERSON, R.layout.item_shop_category_child_view);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_1:
                Level1Item lv0 = (Level1Item) item;
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
                CategoriesBean bean = (CategoriesBean) item;
                holder.setText(R.id.tv, bean.getName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, CommonWebviewActivity.class);
                        intent.putExtra("url", "https://www.chinajcscw.com/mobile/" + bean.getUrl());
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }
}