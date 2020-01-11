package cn.weiben.buildingshopping.ui.adapter;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.manager.GlideManager;
import cn.weiben.buildingshopping.model.HomeBean;
import cn.weiben.buildingshopping.utils.DateUtils;


public class HomePromoteGoodsRvAdapter extends BaseQuickAdapter<HomeBean.PromotionGoodsBean, HomePromoteGoodsRvAdapter.CountDownHolder> {

    public HomePromoteGoodsRvAdapter(@Nullable List<HomeBean.PromotionGoodsBean> data) {
        super(R.layout.item_home_goods_view, data);
    }

    @Override
    protected void convert(@NonNull CountDownHolder helper, HomeBean.PromotionGoodsBean item) {
        if (helper.timer != null) {
            helper.timer.cancel();
        }
        helper.setGone(R.id.tvTime,true);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss", java.util.Locale.US);
        try {
            Date date = sdf.parse(item.getGmt_end_time());
            LogUtils.e(item.getGmt_end_time(),date,date.getTime());
            long left = date.getTime() - System.currentTimeMillis();
            if (left <= 0) {
                helper.timer = null;
                helper.setText(R.id.tvTime, "倒计时结束");
            } else {
                helper.setText(R.id.tvTime,DateUtils.secToTime((int) (left / 1000 + (left % 1000 == 0 ? 0 : 1))));
                helper.timer = new CustomCountDown(left, 1000, helper.getView(R.id.tvTime));
                helper.timer.start();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        GlideManager.INSTANCE.loadImgCenterInside(item.getThumb(), helper.getView(R.id.ivImage));
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvPromotePrice, item.getShop_price());
        TextView tvFinalPrice = helper.getView(R.id.tvFinalPrice);
        SpanUtils.with(tvFinalPrice)
                .append(item.getMarket_price())
                .setStrikethrough()
                .create();
    }



    private class CustomCountDown extends CountDownTimer {
        private TextView time;

        public CustomCountDown(long millisInFuture, long countDownInterval, TextView time) {
            super(millisInFuture, countDownInterval);
            this.time = time;
        }

        @Override
        public void onTick(long left) {
            time.setText(DateUtils.secToTime((int) (left / 1000 + (left % 1000 == 0 ? 0 : 1))));
        }

        @Override
        public void onFinish() {
            time.setText("倒计时结束");
        }
    }

    public class CountDownHolder extends BaseViewHolder {
        private CountDownTimer timer;

        public CountDownHolder(View view) {
            super(view);
        }
    }

}