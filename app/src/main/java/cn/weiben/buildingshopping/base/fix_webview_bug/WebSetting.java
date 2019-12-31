package cn.weiben.buildingshopping.base.fix_webview_bug;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.webkit.DownloadListener;
import android.webkit.WebView;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultDownloadImpl;
import com.just.agentweb.WebListenerManager;

public class WebSetting extends AbsAgentWebSettings {

        Activity activity;

        public WebSetting(Activity activity) {
            this.activity = activity;
        }

        private AgentWeb mAgentWeb;

        @Override
        protected void bindAgentWebSupport(AgentWeb agentWeb) {
            this.mAgentWeb = agentWeb;
        }

        @Override
        public WebListenerManager setDownloader(WebView webView, DownloadListener downloadListener) {
            // Fix Android 5.1 crashing:
            // ClassCastException: android.app.ContextImpl cannot be cast to android.app.Activity
            if (downloadListener == null) {
                //Activity activity = getActivityByContext(webView.getContext());
                downloadListener = DefaultDownloadImpl.create(activity, webView, mAgentWeb.getPermissionInterceptor());
            }
            return super.setDownloader(webView, downloadListener);
        }

        /**
         * Copy from com.blankj.utilcode.util.ActivityUtils#getActivityByView
         */
        private Activity getActivityByContext(Context context) {
            if (context instanceof Activity) return (Activity) context;
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            return null;
        }
    }