package cn.weiben.buildingshopping.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.HomeBean;


public class HomeMenuRvAdapter extends BaseQuickAdapter<HomeBean.MenuListBean, BaseViewHolder> {

    public HomeMenuRvAdapter(@Nullable List<HomeBean.MenuListBean> data) {
        super(R.layout.item_home_menu_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeBean.MenuListBean item) {
        GlideManager.INSTANCE.loadCircleImg(item.getMenu_img(), helper.getView(R.id.ivImage));
        helper.setText(R.id.tvName, item.getMenu_name());
    }

}