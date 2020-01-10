package cn.weiben.buildingshopping.ui.mine.bonus;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.BonusBean;
import cn.weiben.buildingshopping.model.UserBean;

public interface BonusContract {
    interface View extends BaseContract.BaseView{

        void setBonusList(int page, List<BonusBean.BounsBean> data);

        void addBonusSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getBonus(int page);

        void addBonus(String bonus_sn);

    }
}
