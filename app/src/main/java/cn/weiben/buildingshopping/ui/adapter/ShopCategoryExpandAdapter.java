//package cn.weiben.buildingshopping.ui.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.weiben.buildingshopping.R;
//import cn.weiben.buildingshopping.model.ShopBean;
//import cn.weiben.buildingshopping.model.ShopDetailsBean;
//
//public class ShopCategoryExpandAdapter extends BaseExpandableListAdapter {
//
//    private final Context context;
//    private List<ShopDetailsBean.CategoriesBean> data;
//    public ShopCategoryExpandAdapter(Context context) {
//        this.context = context;
//    }
//
//    /**
//     * 自定义设置数据方法；
//     * 通过notifyDataSetChanged()刷新数据，可保持当前位置
//     *
//     * @param data 需要刷新的数据
//     */
//    public void setData(List<ShopDetailsBean.CategoriesBean> data) {
//        this.data = data;
//        notifyDataSetChanged();
//    }
//
//
//
//    @Override
//    public int getGroupCount() {
//        if (data != null && data.size() > 0) {
//            return data.size();
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return data.get(groupPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        GroupViewHolder groupViewHolder;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.item_shop_category_group_view, null);
//
//            groupViewHolder = new GroupViewHolder(convertView);
//            convertView.setTag(groupViewHolder);
//        } else {
//            groupViewHolder = (GroupViewHolder) convertView.getTag();
//        }
//
//        ShopDetailsBean.CategoriesBean datasBean = data.get(groupPosition);
//        groupViewHolder.tvTitle.setText(datasBean.getName());
//
//        return convertView;
//    }
//
//    static class GroupViewHolder {
//        @BindView(R.id.tv_title)
//        TextView tvTitle;
//
//        GroupViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//
//
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        if (data.get(groupPosition).getCat_id() != null && data.get(groupPosition).getCat_id().size() > 0) {
//            return data.get(groupPosition).getCat_id().size();
//        } else {
//            return 0;
//        }
//    }
//
//
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return data.get(groupPosition).getCat_id().get(childPosition);
//    }
//
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return false;
//    }
//}
