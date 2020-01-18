package cn.weiben.buildingshopping.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.BaseExpandableListAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
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
        helper.addOnClickListener(R.id.btnCall, R.id.btnCancelOrder);
        RecyclerView recyclerView = helper.getView(R.id.childRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        OrderChildGoodsRvAdapter adapter = new OrderChildGoodsRvAdapter(bean.getGoods_list());
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        switch (bean.getOrder_status1()) {
            case "0":
                helper.setGone(R.id.btnPayOrder, true);
                helper.setGone(R.id.btnCancelOrder, true);
                helper.setGone(R.id.btnSeeLogistic, false);
                helper.setGone(R.id.btnConfirmGoods, false);
                helper.setGone(R.id.btnComment, false);
                helper.setGone(R.id.btnFinish, false);

                if(!bean.isIs_send_buy()){
                    helper.setText(R.id.btnPayOrder,"立即付款");
                }else {
                    helper.setText(R.id.btnPayOrder,"货到付款");
                }
                break;

            case "1":
            case "5":
                helper.setGone(R.id.btnPayOrder, false);
                helper.setGone(R.id.btnCancelOrder, true);
                helper.setGone(R.id.btnSeeLogistic, false);
                helper.setGone(R.id.btnConfirmGoods, true);
                helper.setGone(R.id.btnComment, false);
                helper.setGone(R.id.btnFinish, false);

                helper.getView(R.id.btnConfirmGoods).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String tips = "";
                        if (bean.isIs_back()) {
                            tips = "此单存在正在退货商品，确认收货退款申请将取消。";
                        } else {
                            tips = "您确认已经收到货物了吗？确认收货后不可在申请退货，请确认收到的货物没有问题";
                        }

                        new AlertDialog.Builder(mContext)
                                .setMessage(tips)
                                .setNegativeButton("取消", (dialog, which) -> {
                                    dialog.dismiss();
                                })
                                .setPositiveButton("确认", (dialog, which) -> {
                                    dialog.dismiss();
                                })
                                .create().show();
                    }
                });
                break;
            case "2":
                helper.setGone(R.id.btnPayOrder, false);
                helper.setGone(R.id.btnCancelOrder, false);
                helper.setGone(R.id.btnSeeLogistic, false);
                helper.setGone(R.id.btnConfirmGoods, false);
                helper.setGone(R.id.btnComment, true);
                helper.setGone(R.id.btnFinish, false);
                break;
            default:

                helper.setGone(R.id.btnCancelOrder, false);
                helper.setGone(R.id.btnSeeLogistic, false);
                helper.setGone(R.id.btnConfirmGoods, false);
                helper.setGone(R.id.btnComment, false);
                if(bean.isNeed_buy()){
                    helper.setGone(R.id.btnPayOrder, true);
                    helper.setGone(R.id.btnFinish, false);
                }else {
                    helper.setGone(R.id.btnPayOrder, false);
                    helper.setGone(R.id.btnFinish, true);
                    helper.setText(R.id.btnFinish,"查看订单");
                }

                break;
        }

    }
}
