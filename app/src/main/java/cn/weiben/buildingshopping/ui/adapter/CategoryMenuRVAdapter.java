package cn.weiben.buildingshopping.ui.adapter;

import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.NewsBean;


public class CategoryMenuRVAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int selectItemPosition = 0;

    public CategoryMenuRVAdapter(List<String> data) {
        super(R.layout.item_category_menu_view, data);
    }

    public void setSelectItemPosition(int position) {
        int temp = selectItemPosition;
        selectItemPosition = position;
        notifyItemChanged(temp);
        notifyItemChanged(position);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String bean) {
        if (selectItemPosition == helper.getLayoutPosition()) {
            helper.setGone(R.id.tvTag, true);
            helper.setTextColor(R.id.tv_menu, mContext.getResources().getColor(R.color.colorAccent));
            helper.setBackgroundColor(R.id.tv_menu, Color.parseColor("#F5F5F5"));
        } else {
            helper.setGone(R.id.tvTag, false);
            helper.setTextColor(R.id.tv_menu, Color.parseColor("#241c1d"));
            helper.setBackgroundColor(R.id.tv_menu, Color.WHITE);
        }

        helper.setText(R.id.tv_menu, bean);
    }


}
