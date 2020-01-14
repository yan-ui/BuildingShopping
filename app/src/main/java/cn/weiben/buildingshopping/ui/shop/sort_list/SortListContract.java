package cn.weiben.buildingshopping.ui.shop.sort_list;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.GoodsBean;

/**
 * 主页配置约定
 */
public interface SortListContract {
    interface View extends BaseContract.BaseView{
        void setSortList(int page, List<GoodsBean> datas);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getSortList(String id, String sort, int page, int last, int amount, String order);
    }
}
