package cn.weiben.buildingshopping.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.CartPayBean;
import cn.weiben.buildingshopping.model.GoodsBean;
import cn.weiben.buildingshopping.model.ShopBean;
import cn.weiben.buildingshopping.ui.home.goods_detail.GoodsDetailActivity;


public class ShippingTypeRvAdapter extends BaseQuickAdapter<CartPayBean.ShippingListBean, BaseViewHolder> {

    private int index = -1;//标记当前选择的选项

    public ShippingTypeRvAdapter(@Nullable List<CartPayBean.ShippingListBean> data) {
        super(R.layout.item_shipping_type_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CartPayBean.ShippingListBean item) {
        RadioButton radioButton = helper.getView(R.id.rb_question_item);
        radioButton.setText(item.getShipping_name());

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(listener != null){
                        listener.onCheck(item.getShipping_id());
                    }
                    index = helper.getLayoutPosition();
                    notifyDataSetChanged();
                }
            }
        });

        if (index == helper.getLayoutPosition()) {
            radioButton.setChecked(true);
        } else {
            radioButton.setChecked(false);
        }

    }

    private IOnCheckListener listener;
    public void setIOnCheckListener(IOnCheckListener listener){
        this.listener = listener;
    }

    public interface IOnCheckListener{
        void onCheck(String id);
    }


}