package cn.weiben.buildingshopping.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.GoodsDetail;

/**
 * Created by xiangcheng on 17/9/26.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsDetail.SpecificationBean> classifies;
    private Context context;

    public ProductAdapter(Context context, List<GoodsDetail.SpecificationBean> classifies) {
        this.context = context;
        this.classifies = classifies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductHolder(View.inflate(context, R.layout.product_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProductHolder productHolder = (ProductHolder) holder;
        GoodsDetail.SpecificationBean classify = classifies.get(position);

        final FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        productHolder.title.setText(classify.getName());
        if (productHolder.itemView.getTag() == null) {
            productHolder.des.addItemDecoration(new SpaceItemDecoration(dp2px(10)));
            productHolder.itemView.setTag("item");
        }
        productHolder.des.setLayoutManager(flowLayoutManager);
        productHolder.des.setAdapter(new FlowAdapter(classify.getValues()));
    }

    public String getTitle(int position) {
        return classifies.get(position).getName();
    }

    @Override
    public int getItemCount() {
        return classifies.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView des;

        public ProductHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            des = (RecyclerView) itemView.findViewById(R.id.des);
        }
    }

    class FlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<GoodsDetail.SpecificationBean.ValuesBean> list;
        private GoodsDetail.SpecificationBean.ValuesBean selectDes;

        public FlowAdapter(List<GoodsDetail.SpecificationBean.ValuesBean> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(context, R.layout.flow_item, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            TextView textView = ((MyHolder) holder).text;

            final GoodsDetail.SpecificationBean.ValuesBean des = list.get(position);
            if(selectDes == null){
                des.setSelect(true);
                selectDes = des;

                if(listener != null){
                    listener.itemSelected(selectDes);
                }
            }

            if (des.isSelect()) {
                textView.setTextColor(Color.WHITE);
                textView.setBackground(context.getResources().getDrawable(R.drawable.shape_color_red_radius_5_bg));
            } else {
                textView.setTextColor(Color.parseColor("#333333"));
                textView.setBackground(context.getResources().getDrawable(R.drawable.shape_stroke_gray_color_white_radius_5_bg));
            }
            textView.setText(des.getLabel());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (des != selectDes) {
                        if (selectDes != null) {
                            selectDes.setSelect(false);
                        }
                    }
                    des.setSelect(true);
                    selectDes = des;
                    notifyDataSetChanged();
                    if(listener != null){
                        listener.itemSelected(selectDes);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            private TextView text;

            public MyHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.flow_text);
            }
        }
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


    private IOnItemSelectedListener listener;
    public void setIOnItemSelectedListener(IOnItemSelectedListener listener){
        this.listener = listener;
    }
    public interface IOnItemSelectedListener{
        void itemSelected(GoodsDetail.SpecificationBean.ValuesBean bean);
    }

}