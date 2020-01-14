package cn.weiben.buildingshopping.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import cn.weiben.buildingshopping.ui.adapter.ExpandableItemAdapter;

public class GoodsLevelItem extends AbstractExpandableItem<ShopDetailsBean.CategoryGoodsBean.GoodsBean> implements MultiItemEntity {

    public String title;
    public String url;

    public GoodsLevelItem(String title, String url) {
        this.title = title;
        this.url = url;
    }


    @Override
    public int getLevel() {
        return 0;
    }


    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
