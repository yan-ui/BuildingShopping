package cn.weiben.buildingshopping.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.LeaveMessageBean;
import cn.weiben.buildingshopping.model.NewsBean;


public class LeaveMessageAdapter extends BaseQuickAdapter<LeaveMessageBean.MessageListBean, BaseViewHolder> {

    public LeaveMessageAdapter(List<LeaveMessageBean.MessageListBean> data) {
        super(R.layout.item_leave_message_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LeaveMessageBean.MessageListBean bean) {
        helper.setText(R.id.tvTypeName, bean.getMsg_type())
                .setText(R.id.tvTime, bean.getMsg_time())
                .setText(R.id.tvTitle, "标题："+bean.getMsg_title())
                .setText(R.id.tvContent, bean.getMsg_content());

    }
}
