package cn.weiben.buildingshopping.ui.adapter;

import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.AddressModel;
import cn.weiben.buildingshopping.model.BonusBean;


public class AddressListRvAdapter extends BaseQuickAdapter<AddressModel, BaseViewHolder> {

    public AddressListRvAdapter(@Nullable List<AddressModel> data) {
        super(R.layout.item_address_list_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AddressModel item) {
        helper.addOnClickListener(R.id.btnEdit, R.id.btnDelete, R.id.rbDefault);

        RadioButton rbDefault = helper.getView(R.id.rbDefault);
        if (item.isIs_default()) {
            rbDefault.setChecked(true);
            rbDefault.setText("默认地址");
        } else {
            rbDefault.setChecked(false);
            rbDefault.setText("设为默认地址");
        }

        helper.setText(R.id.tvNickName,item.getConsignee())
        .setText(R.id.tvAddress,item.getCountry_name()+item.getProvince_name()+item.getCity_name()+item.getDistrict_name()+item.getXiangcun_name()+item.getAddress())
        .setText(R.id.tvMobile,item.getTel());

    }

}