package cn.weiben.buildingshopping.ui.mine.setting;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.UserBean;
import cn.weiben.buildingshopping.model.UserEditBean;

public interface UserSettingContract {
    interface View extends BaseContract.BaseView{
        void setUser(UserEditBean data);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getUser();
        void setUserProfile(String headimg,String user_name,String birthday,int sex,String email);
        void setUserPassword(String oldPass,String newPass,String confirmPass);
    }
}
