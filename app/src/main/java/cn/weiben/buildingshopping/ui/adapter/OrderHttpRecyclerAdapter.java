package cn.weiben.buildingshopping.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.OrderResultBean;


public class OrderHttpRecyclerAdapter extends BaseQuickAdapter<OrderResultBean.OrderListBean, BaseViewHolder> {

    public OrderHttpRecyclerAdapter(List<OrderResultBean.OrderListBean> data) {
        super(R.layout.item_order_list_status_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderResultBean.OrderListBean bean) {
        helper.setText(R.id.tvShopName, bean.getShopname())
                .setText(R.id.tvStatus, bean.getOrder_status())
                .setText(R.id.tvTotalPrice, bean.getTotal_fee())
                .setText(R.id.tvGoodsCount, "共" + bean.getCount() + "件商品 实付：");
        helper.addOnClickListener(R.id.btnCall);
        RecyclerView recyclerView = helper.getView(R.id.childRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        OrderChildGoodsRvAdapter adapter = new OrderChildGoodsRvAdapter(bean.getGoods_list());
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }
}
