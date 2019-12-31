package cn.weiben.buildingshopping.ui.category;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.CategoryBean;
import cn.weiben.buildingshopping.model.HomeBean;

/**
 * 主页配置约定
 */
public interface CategoryContract {
    interface View extends BaseContract.BaseView{
        void setCategoryList(List<CategoryBean> datas);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getCategoryList();
    }
}
