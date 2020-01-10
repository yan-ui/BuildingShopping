package cn.weiben.buildingshopping.ui.mine.all_money;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.MoneyManagerBean;
import cn.weiben.buildingshopping.model.UserBean;

public interface AllMoneyContract {
    interface View extends BaseContract.BaseView{
        void setUser(MoneyManagerBean data);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getUser();
    }
}
