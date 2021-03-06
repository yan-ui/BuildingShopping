package cn.weiben.buildingshopping.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.model.CartBean;


/**
 * 购物车的adapter
 * 因为使用的是ExpandableListView，所以继承BaseExpandableListAdapter
 */
public class ShoppingCarAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final LinearLayout llSelectAll;
    private final ImageView ivSelectAll;
    private final Button btnOrder;
    private final Button btnDelete;
    private final RelativeLayout rlTotalPrice;
    private final TextView tvTotalPrice;
    private List<CartBean.GoodsListBeanX> data;
    private boolean isSelectAll = false;
    private double total_price;

    public ShoppingCarAdapter(Context context, LinearLayout llSelectAll,
                              ImageView ivSelectAll, Button btnOrder, Button btnDelete,
                              RelativeLayout rlTotalPrice, TextView tvTotalPrice) {
        this.context = context;
        this.llSelectAll = llSelectAll;
        this.ivSelectAll = ivSelectAll;
        this.btnOrder = btnOrder;
        this.btnDelete = btnDelete;
        this.rlTotalPrice = rlTotalPrice;
        this.tvTotalPrice = tvTotalPrice;
    }

    /**
     * 自定义设置数据方法；
     * 通过notifyDataSetChanged()刷新数据，可保持当前位置
     *
     * @param data 需要刷新的数据
     */
    public void setData(List<CartBean.GoodsListBeanX> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_group, null);

            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        final CartBean.GoodsListBeanX datasBean = data.get(groupPosition);
        //店铺名称
        String store_name = datasBean.getSupplier_name();

        if (store_name != null) {
            groupViewHolder.tvStoreName.setText(store_name);
        } else {
            groupViewHolder.tvStoreName.setText("");
        }

        //店铺内的商品都选中的时候，店铺的也要选中
        for (int i = 0; i < datasBean.getGoods_list().size(); i++) {
            CartBean.GoodsListBeanX.GoodsListBean goodsBean = datasBean.getGoods_list().get(i);
            boolean isSelect = goodsBean.getIsSelect();
            if (isSelect) {
                datasBean.setIsSelect_shop(true);
            } else {
                datasBean.setIsSelect_shop(false);
                break;
            }
        }

        //因为set之后要重新get，所以这一块代码要放到一起执行
        //店铺是否在购物车中被选中
        final boolean isSelect_shop = datasBean.getIsSelect_shop();
        if (isSelect_shop) {
            groupViewHolder.ivSelect.setImageResource(R.mipmap.select);
        } else {
            groupViewHolder.ivSelect.setImageResource(R.mipmap.unselect);
        }

        //店铺选择框的点击事件
        groupViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datasBean.setIsSelect_shop(!isSelect_shop);

                List<CartBean.GoodsListBeanX.GoodsListBean> goods = datasBean.getGoods_list();
                for (int i = 0; i < goods.size(); i++) {
                    CartBean.GoodsListBeanX.GoodsListBean goodsBean = goods.get(i);
                    goodsBean.setIsSelect(!isSelect_shop);
                }
                notifyDataSetChanged();
            }
        });

        //当所有的选择框都是选中的时候，全选也要选中
        w:
        for (int i = 0; i < data.size(); i++) {
            List<CartBean.GoodsListBeanX.GoodsListBean> goods = data.get(i).getGoods_list();
            for (int y = 0; y < goods.size(); y++) {
                CartBean.GoodsListBeanX.GoodsListBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.getIsSelect();
                if (isSelect) {
                    isSelectAll = true;
                } else {
                    isSelectAll = false;
                    break w;//根据标记，跳出嵌套循环
                }
            }
        }
        if (isSelectAll) {
            ivSelectAll.setBackgroundResource(R.mipmap.select);
        } else {
            ivSelectAll.setBackgroundResource(R.mipmap.unselect);
        }

        //全选的点击事件
        llSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAll = !isSelectAll;

                if (isSelectAll) {
                    for (int i = 0; i < data.size(); i++) {
                        List<CartBean.GoodsListBeanX.GoodsListBean> goods = data.get(i).getGoods_list();
                        for (int y = 0; y < goods.size(); y++) {
                            CartBean.GoodsListBeanX.GoodsListBean goodsBean = goods.get(y);
                            goodsBean.setIsSelect(true);
                        }
                    }
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        List<CartBean.GoodsListBeanX.GoodsListBean> goods = data.get(i).getGoods_list();
                        for (int y = 0; y < goods.size(); y++) {
                            CartBean.GoodsListBeanX.GoodsListBean goodsBean = goods.get(y);
                            goodsBean.setIsSelect(false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        //合计的计算
        total_price = 0.0;
        tvTotalPrice.setText("¥0.00");
        for (int i = 0; i < data.size(); i++) {
            List<CartBean.GoodsListBeanX.GoodsListBean> goods = data.get(i).getGoods_list();
            for (int y = 0; y < goods.size(); y++) {
                CartBean.GoodsListBeanX.GoodsListBean goodsBean = goods.get(y);
                boolean isSelect = goodsBean.getIsSelect();
                if (isSelect) {
                    //todo:不确定数据
                    String num = "" + goodsBean.getGoods_number();
                    String price = goodsBean.getGoods_price();

                    double v = Double.parseDouble(num);
                    double v1 = Double.parseDouble(price);

                    total_price = total_price + v * v1;

                    //让Double类型完整显示，不用科学计数法显示大写字母E
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    tvTotalPrice.setText("¥" + decimalFormat.format(total_price));
                }
            }
        }

        //去结算的点击事件
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建临时的List，用于存储有商品被选中的店铺
                List<CartBean.GoodsListBeanX> tempStores = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    //店铺中是否有商品被选中
                    boolean hasGoodsSelect = false;
                    //创建临时的List，用于存储被选中的商品
                    List<CartBean.GoodsListBeanX.GoodsListBean> tempGoods = new ArrayList<>();

                    CartBean.GoodsListBeanX storesBean = data.get(i);
                    List<CartBean.GoodsListBeanX.GoodsListBean> goods = storesBean.getGoods_list();
                    for (int y = 0; y < goods.size(); y++) {
                        CartBean.GoodsListBeanX.GoodsListBean goodsBean = goods.get(y);
                        boolean isSelect = goodsBean.getIsSelect();
                        if (isSelect) {
                            hasGoodsSelect = true;
                            tempGoods.add(goodsBean);
                        }
                    }

                    if (hasGoodsSelect) {
                        CartBean.GoodsListBeanX storeBean = new CartBean.GoodsListBeanX();
                        storeBean.setSupplier_name(storesBean.getSupplier_name());
                        storeBean.setGoods_list(tempGoods);

                        tempStores.add(storeBean);
                    }
                }

                if (tempStores != null && tempStores.size() > 0) {//如果有被选中的
                    /**
                     * 实际开发中，如果有被选中的商品，
                     * 则跳转到确认订单页面，完成后续订单流程。
                     */
                    if (mBuyListener != null) {
                        List<String> idList = new ArrayList<>();

                        for (int i = 0; i < tempStores.size(); i++) {
                            List<CartBean.GoodsListBeanX.GoodsListBean> goods = data.get(i).getGoods_list();

                            for (int j = 0; j < goods.size(); j++) {
                                if (goods.get(j).getIsSelect()) {
                                    idList.add(goods.get(j).getRec_id());
                                }
                            }
                        }

                        mBuyListener.onBuyGoods(idList);
                    }
                } else {
                    ToastUtils.showShort("请选择要购买的商品");
                }
            }
        });

        //删除的点击事件
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 实际开发中，通过回调请求后台接口实现删除操作
                 */
                if (mDeleteListener != null) {
                    mDeleteListener.onDelete();
                }
            }
        });

        return convertView;
    }

    static class GroupViewHolder {
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.ll)
        LinearLayout ll;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //------------------------------------------------------------------------------------------------
    @Override
    public int getChildrenCount(int groupPosition) {
        if (data.get(groupPosition).getGoods_list() != null && data.get(groupPosition).getGoods_list().size() > 0) {
            return data.get(groupPosition).getGoods_list().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getGoods_list().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_child, null);

            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final CartBean.GoodsListBeanX datasBean = data.get(groupPosition);
        //店铺名称
        String store_name = datasBean.getSupplier_name();
        //店铺是否在购物车中被选中
        final boolean isSelect_shop = datasBean.getIsSelect_shop();
        final CartBean.GoodsListBeanX.GoodsListBean goodsBean = datasBean.getGoods_list().get(childPosition);
        //商品图片
        //todo:商品图片
        String goods_image = goodsBean.getGoods_thumb();
        //商品ID
        final String goods_id = goodsBean.getGoods_id();
        //商品名称
        String goods_name = goodsBean.getGoods_name();
        //商品价格
        String goods_price = goodsBean.getGoods_price();
        //商品市场价格
        String goods_market_price = goodsBean.getMarket_price();
        //商品规格
        String goods_spec_value = goodsBean.getGoods_attr();
        //商品数量
        //todo:商品数量
        String goods_num = "" + goodsBean.getGoods_number();
        //商品是否被选中
        final boolean isSelect = goodsBean.getIsSelect();

        Glide.with(context)
                .load(goods_image)
                .into(childViewHolder.ivPhoto);
        if (goods_name != null) {
            childViewHolder.tvName.setText(goods_name);
        } else {
            childViewHolder.tvName.setText("");
        }
        if (goods_price != null) {
            childViewHolder.tvPriceValue.setText(goods_price);
        } else {
            childViewHolder.tvPriceValue.setText("");
        }
        if (goods_market_price != null) {
            SpanUtils.with(childViewHolder.tvOldPriceValue).append(goods_market_price).setStrikethrough().create();
        } else {
            childViewHolder.tvOldPriceValue.setText("");
        }
        if (goods_num != null) {
            childViewHolder.tvEditBuyNumber.setText(goods_num);
        } else {
            childViewHolder.tvEditBuyNumber.setText("");
        }

        if (goods_spec_value != null) {
            childViewHolder.tvSpecValue.setText(goods_spec_value);
        } else {
            childViewHolder.tvSpecValue.setText("");
        }

        //商品是否被选中
        if (isSelect) {
            childViewHolder.ivSelect.setImageResource(R.mipmap.select);
        } else {
            childViewHolder.ivSelect.setImageResource(R.mipmap.unselect);
        }

        //商品选择框的点击事件
        childViewHolder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsBean.setIsSelect(!isSelect);
                if (!isSelect == false) {
                    datasBean.setIsSelect_shop(false);
                }
                notifyDataSetChanged();
            }
        });

        //加号的点击事件
        childViewHolder.ivEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟加号操作
                //todo:商品数量
                String num = "" + goodsBean.getGoods_number();
                Integer integer = Integer.valueOf(num);
                integer++;
                goodsBean.setGoods_number(integer + "");
                notifyDataSetChanged();

                /**
                 * 实际开发中，通过回调请求后台接口实现数量的加减
                 */
                if (mChangeCountListener != null) {
                    mChangeCountListener.onChangeCount(goods_id);
                }
            }
        });
        //减号的点击事件
        childViewHolder.ivEditSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟减号操作
                String num = "" + goodsBean.getGoods_number();
                Integer integer = Integer.valueOf(num);
                if (integer > 1) {
                    integer--;
                    goodsBean.setGoods_number(integer + "");

                    /**
                     * 实际开发中，通过回调请求后台接口实现数量的加减
                     */
                    if (mChangeCountListener != null) {
                        mChangeCountListener.onChangeCount(goods_id);
                    }
                } else {
                    ToastUtils.showShort("商品不能再减少了");
                }
                notifyDataSetChanged();
            }
        });

        if (childPosition == data.get(groupPosition).getGoods_list().size() - 1) {
            childViewHolder.view.setVisibility(View.GONE);
            childViewHolder.viewLast.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.view.setVisibility(View.VISIBLE);
            childViewHolder.viewLast.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ChildViewHolder {
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_spec_value)
        TextView tvSpecValue;
        @BindView(R.id.tv_old_price_value)
        TextView tvOldPriceValue;
        @BindView(R.id.tv_price_key)
        TextView tvPriceKey;
        @BindView(R.id.tv_price_value)
        TextView tvPriceValue;
        @BindView(R.id.iv_edit_subtract)
        ImageView ivEditSubtract;
        @BindView(R.id.tv_edit_buy_number)
        TextView tvEditBuyNumber;
        @BindView(R.id.iv_edit_add)
        ImageView ivEditAdd;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.view_last)
        View viewLast;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //-----------------------------------------------------------------------------------------------

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //删除的回调
    public interface OnDeleteListener {
        void onDelete();
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        mDeleteListener = listener;
    }

    private OnDeleteListener mDeleteListener;

    //修改商品数量的回调
    public interface OnChangeCountListener {
        void onChangeCount(String goods_id);
    }

    public void setOnChangeCountListener(OnChangeCountListener listener) {
        mChangeCountListener = listener;
    }

    private OnChangeCountListener mChangeCountListener;


    //结算的回调
    public interface OnBuyListener {
        void onBuyGoods(List<String> idList);
    }

    public void setOnBuyListener(OnBuyListener listener) {
        mBuyListener = listener;
    }

    private OnBuyListener mBuyListener;
}