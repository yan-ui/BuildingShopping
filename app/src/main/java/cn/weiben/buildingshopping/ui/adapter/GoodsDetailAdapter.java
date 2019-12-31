package cn.weiben.buildingshopping.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.GoodsDetail;
import cn.weiben.buildingshopping.model.NewsBean;


public class GoodsDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public GoodsDetailAdapter(List<String> data) {
        super(R.layout.item_my_integral_detail, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String bean) {



    }
}
