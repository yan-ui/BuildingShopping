package cn.weiben.buildingshopping.ui.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.ChildBean;
import cn.weiben.buildingshopping.ui.category.sort_list.SortListActivity;


public class CategoryContentRVAdapter extends BaseQuickAdapter<ChildBean, BaseViewHolder> {


    public CategoryContentRVAdapter(List<ChildBean> data) {
        super(R.layout.item_category_content_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ChildBean bean) {
        helper.setText(R.id.tvName, bean.getName());

        RecyclerView childGridRecyclerView = helper.getView(R.id.childGridRecyclerView);
        childGridRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        CategoryChildGoodsRvAdapter childGoodsRvAdapter = new CategoryChildGoodsRvAdapter(bean.getChild());
        childGoodsRvAdapter.bindToRecyclerView(childGridRecyclerView);
        childGridRecyclerView.setAdapter(childGoodsRvAdapter);
        childGoodsRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ChildBean childBean = childGoodsRvAdapter.getData().get(position);
                Intent intent = new Intent(mContext, SortListActivity.class);
                intent.putExtra("id",childBean.getId());
                mContext.startActivity(intent);
            }
        });

    }


}
