package cn.weiben.buildingshopping.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

import cn.weiben.buildingshopping.R;

/**
 * Created by aoc on 2017/4/11.
 */

public class PriceUpDownView extends LinearLayout implements View.OnClickListener {

    private TextView priceView;
    private boolean isUp;
    private Callback callback;

    public PriceUpDownView(Context context) {
        this(context, null);
    }

    public PriceUpDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PriceUpDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.price_up_down, this);
        priceView = (TextView) findViewById(R.id.price_id);
        setOnClickListener(this);
//        priceView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setDrawable();
        if (callback != null)
            callback.getStatus(isUp);
//        switch (v.getId()) {
//            case R.id.price_id:
//
//                break;
//        }
    }

    private void setDrawable() {
        LogUtils.e(isUp);
        Drawable drawable = null;
        if (isUp) {
            isUp = false;
            drawable = getResources().getDrawable(R.mipmap.down);
        } else {
            isUp = true;
            drawable = getResources().getDrawable(R.mipmap.up);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        priceView.setCompoundDrawables(null, null, drawable, null);
        priceView.setCompoundDrawablePadding(8);
        priceView.setTextColor(Color.parseColor("#e02b4d"));
    }

    public void restore(boolean isClick) {
        Drawable drawable = null;
        if (isClick) {
            isUp = false;
            drawable = getResources().getDrawable(R.mipmap.down);
            priceView.setTextColor(Color.parseColor("#e02b4d"));
        } else {
            drawable = getResources().getDrawable(R.mipmap.angle_s);
            priceView.setTextColor(Color.parseColor("#666666"));
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        priceView.setCompoundDrawables(null, null, drawable, null);
        priceView.setCompoundDrawablePadding(8);

    }

    public interface Callback {
        void getStatus(boolean isUp);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}