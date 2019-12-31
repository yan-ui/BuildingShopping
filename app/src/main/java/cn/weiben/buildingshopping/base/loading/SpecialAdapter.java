package cn.weiben.buildingshopping.base.loading;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.billy.android.loading.Gloading;
import com.wang.avi.AVLoadingIndicatorView;


import cn.weiben.buildingshopping.R;

import static com.billy.android.loading.Gloading.STATUS_LOADING;

/**
 * demo:
 * when status == STATUS_LOADING use another UI
 * otherwise, use the same UI as the global status view
 *
 * @author billy.qi
 * @since 19/3/19 23:20
 */
public class SpecialAdapter implements Gloading.Adapter {

    public static final int STATUS_PAGE_LOADING = 5;

    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        switch (status) {
            case STATUS_LOADING:
                //only loading UI special
                SpecialLoadingStatusView view;
                if (convertView == null || !(convertView instanceof SpecialLoadingStatusView)) {
                    view = new SpecialLoadingStatusView(holder.getContext());
                    convertView = view;
                } else {
                    view = (SpecialLoadingStatusView) convertView;
                }
                view.start();
                break;
            case STATUS_PAGE_LOADING:
                //only loading UI special
                PageLoadingStatusView pageLoadingStatusView;
                if (convertView == null || !(convertView instanceof PageLoadingStatusView)) {
                    pageLoadingStatusView = new PageLoadingStatusView(holder.getContext());
                    convertView = pageLoadingStatusView;
                } else {
                    pageLoadingStatusView = (PageLoadingStatusView) convertView;
                }
                pageLoadingStatusView.start();
                break;
            default:
                //other status use global UI
                GlobalLoadingStatusView globalLoadingStatusView;
                if (convertView == null || !(convertView instanceof GlobalLoadingStatusView)) {
                    globalLoadingStatusView = new GlobalLoadingStatusView(holder.getContext(), holder.getRetryTask());
                    convertView = globalLoadingStatusView;
                } else {
                    globalLoadingStatusView = (GlobalLoadingStatusView) convertView;
                }
                globalLoadingStatusView.setStatus(status);
                break;
        }


        return convertView;
    }

    /**
     * 请求数据Loading（透明）
     */
    class SpecialLoadingStatusView extends RelativeLayout {

        private final AVLoadingIndicatorView lvFinePoiStar;

        public SpecialLoadingStatusView(Context context) {
            super(context);
            setGravity(Gravity.CENTER);
            LayoutInflater.from(context).inflate(R.layout.view_special_loading, this, true);

            lvFinePoiStar = findViewById(R.id.loading_anim);

        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            lvFinePoiStar.smoothToHide();
        }

        public void start() {
            lvFinePoiStar.smoothToShow();
        }
    }


    /**
     * 加载页面Loading（不透明）
     */
    class PageLoadingStatusView extends RelativeLayout {

        private final AVLoadingIndicatorView lvFinePoiStar;

        public PageLoadingStatusView(Context context) {
            super(context);
            setGravity(Gravity.CENTER);
            LayoutInflater.from(context).inflate(R.layout.view_page_loading, this, true);

            lvFinePoiStar = findViewById(R.id.loading_anim);

        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            lvFinePoiStar.smoothToHide();
        }

        public void start() {
            lvFinePoiStar.smoothToShow();
        }
    }

}