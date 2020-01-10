package cn.weiben.buildingshopping.ui.mine.leave_message.add_message;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.UserBean;

public interface AddMessageContract {
    interface View extends BaseContract.BaseView{

    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void addMessage(int type,String msg_title,String msg_content);
    }
}
