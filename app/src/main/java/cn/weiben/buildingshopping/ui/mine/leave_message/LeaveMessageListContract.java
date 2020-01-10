package cn.weiben.buildingshopping.ui.mine.leave_message;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.LeaveMessageBean;
import cn.weiben.buildingshopping.model.UserBean;

public interface LeaveMessageListContract {
    interface View extends BaseContract.BaseView{
        void setData(int page,List<LeaveMessageBean.MessageListBean> data);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getLeaveMessageList(int page);
    }
}
