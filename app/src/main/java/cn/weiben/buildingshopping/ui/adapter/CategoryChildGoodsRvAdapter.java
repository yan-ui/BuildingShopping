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
import cn.weiben.buildingshopping.model.ChildBean;
import cn.weiben.buildingshopping.model.HomeBean;


public class CategoryChildGoodsRvAdapter extends BaseQuickAdapter<ChildBean, BaseViewHolder> {

    public CategoryChildGoodsRvAdapter(@Nullable List<ChildBean> data) {
        super(R.layout.item_category_child_goods_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ChildBean item) {
        GlideManager.INSTANCE.loadImgCenterInside(item.getImg(), helper.getView(R.id.ivImage));
        helper.setText(R.id.tvName, item.getName());
    }

}