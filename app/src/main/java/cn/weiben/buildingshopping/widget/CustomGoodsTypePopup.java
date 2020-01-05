package cn.weiben.buildingshopping.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.GoodsDetail;
import cn.weiben.buildingshopping.ui.adapter.ProductAdapter;

public class CustomGoodsTypePopup extends BottomPopupView {
    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    private GoodsDetail datas;

    public CustomGoodsTypePopup(@NonNull Context context, GoodsDetail data) {
        super(context);
        this.datas = data;
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_goods_type_popup;
    }

    private GoodsDetail.SpecificationBean.ValuesBean selectBean;

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvStoreNum = findViewById(R.id.tvStoreNum);
        TextView tvGoodsType = findViewById(R.id.tvGoodsType);
        ImageView ivImage = findViewById(R.id.ivImage);
        AddSubUtils addWidget = findViewById(R.id.addWidget);

        ProductAdapter adapter = new ProductAdapter(getContext(),datas.getSpecification());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setIOnItemSelectedListener(new ProductAdapter.IOnItemSelectedListener() {
            @Override
            public void itemSelected(GoodsDetail.SpecificationBean.ValuesBean bean) {
                selectBean = bean;
                if(listener != null){
                    listener.itemSelected(bean);
                }
                GlideManager.INSTANCE.loadRoundImg(bean.getGoods_attr_thumb(),ivImage);
                tvPrice.setText("价格："+bean.getFormat_price());
                tvStoreNum.setText("库存："+bean.getSelected_key_ecshop68());
                tvGoodsType.setText("规格："+bean.getLabel());
            }
        });


        findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });

        findViewById(R.id.btnSubmit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectBean == null){
                    ToastUtils.showShort("请选择规格");
                    return;
                }
                if(listener != null){
                    listener.itemSubmit(selectBean,addWidget.getNumber());
                }
                dismiss(); // 关闭弹窗
            }
        });
    }

    /**
     * 弹窗的高度，用来动态设定当前弹窗的高度，受getMaxHeight()限制
     *
     * @return
     */
    protected int getPopupHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .85f);
    }


    private IOnItemSelectedListener listener;
    public void setIOnItemSelectedListener(IOnItemSelectedListener listener){
        this.listener = listener;
    }
    public interface IOnItemSelectedListener{
        void itemSelected(GoodsDetail.SpecificationBean.ValuesBean bean);
        void itemSubmit(GoodsDetail.SpecificationBean.ValuesBean bean, int number);
    }

}