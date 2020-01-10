package cn.weiben.buildingshopping.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.GoodsDetail;
import cn.weiben.buildingshopping.ui.adapter.CustomGoodsParamPopupAdapter;
import cn.weiben.buildingshopping.ui.adapter.CustomRankPricePopupAdapter;
import cn.weiben.buildingshopping.utils.RecycleViewDivider;

public class CustomGoodsParamPopup extends BottomPopupView {
    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    private List<GoodsDetail.RankPricesBean> rankPricesBeanList;

    public CustomGoodsParamPopup(@NonNull Context context, List<GoodsDetail.RankPricesBean> data) {
        this(context, data, 1);
    }


    private int type = 1;
    private String typeStr = "";

    public CustomGoodsParamPopup(@NonNull Context context, List<GoodsDetail.RankPricesBean> data, int type) {
        super(context);
        this.type = type;
        this.rankPricesBeanList = data;
    }

    public CustomGoodsParamPopup(@NonNull Context context, List<GoodsDetail.RankPricesBean> data, String type) {
        super(context);
        this.typeStr = type;
        this.rankPricesBeanList = data;
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_list_popup;
    }

    private RecyclerView recyclerView;

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        recyclerView = findViewById(R.id.recyclerView);

        TextView tvTitle = findViewById(R.id.tvTitle);

        if (!StringUtils.isEmpty(typeStr)) {
            tvTitle.setText(typeStr);
            CustomGoodsParamPopupAdapter adapter = new CustomGoodsParamPopupAdapter(rankPricesBeanList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter.bindToRecyclerView(recyclerView);
            recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayout.VERTICAL));
            recyclerView.setAdapter(adapter);
        } else {
            if (type == 1) {
                tvTitle.setText("会员专享价");
                CustomRankPricePopupAdapter adapter = new CustomRankPricePopupAdapter(rankPricesBeanList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter.bindToRecyclerView(recyclerView);
                recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(adapter);
            } else {
                tvTitle.setText("商品信息");
                CustomGoodsParamPopupAdapter adapter = new CustomGoodsParamPopupAdapter(rankPricesBeanList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter.bindToRecyclerView(recyclerView);
                recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

        }


        findViewById(R.id.tv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });
    }

    // 设置最大宽度，看需要而定
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }

    // 设置最大高度，看需要而定
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }

    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }

    /**
     * 弹窗的高度，用来动态设定当前弹窗的高度，受getMaxHeight()限制
     *
     * @return
     */
    protected int getPopupHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .85f);
    }
}