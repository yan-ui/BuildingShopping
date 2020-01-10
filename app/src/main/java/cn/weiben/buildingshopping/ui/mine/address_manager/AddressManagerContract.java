package cn.weiben.buildingshopping.ui.mine.address_manager;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.AddressModel;


public interface AddressManagerContract {
    interface View extends BaseContract.BaseView{
       void setAddressList(List<AddressModel> datas);
        void deleteSuccess();
        void setDefaultSuccess();
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAddressList();
        void deleteAddress(String id);
        void setDefaultAddress(String id);
    }
}
