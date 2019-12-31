package cn.weiben.buildingshopping.ui.test.list;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.NewsBean;


public interface ListContract {
    interface View extends BaseContract.BaseView{
       void setData(int page,List<NewsBean> list);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
       void getList(int page);
       void getListNoLoading(int page);
    }

}
