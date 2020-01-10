package cn.weiben.buildingshopping.ui.mine.address_manager.add_address;

import java.util.List;

import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.model.AddressModel;
import cn.weiben.buildingshopping.model.UserBean;
import cn.weiben.buildingshopping.model.address_picker.AddressBean;

public interface AddAddressContract {
    interface View extends BaseContract.BaseView{
        void setAddressDetails(AddressModel bean);
        void addSuccess();
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void addAddressArea(String consignee,String email,String province,String city,String district,String address,String mobile,String zipcode,String address_id);
        void getAddressDetails(String address_id);
    }
}
