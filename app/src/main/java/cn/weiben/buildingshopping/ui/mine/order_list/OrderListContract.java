package cn.weiben.buildingshopping.ui.mine.order_list;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.OrderResultBean;


public interface OrderListContract {
    interface View extends BaseContract.BaseView{

        void setData(int page, List<OrderResultBean.OrderListBean> order_list);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
       void getOrderList(int page,int composite_status);
    }

}
