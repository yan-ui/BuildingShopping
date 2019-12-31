package cn.weiben.buildingshopping.api;

import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import cn.weiben.buildingshopping.App;
import cn.weiben.buildingshopping.api.model.BaseResult;
import cn.weiben.buildingshopping.common.Contacts;
import cn.weiben.buildingshopping.manager.AccountHelper;
import cn.weiben.buildingshopping.ui.account.login.LoginActivity;
import cn.weiben.buildingshopping.ui.test.PageStatusTestActivity;
import cn.weiben.buildingshopping.utils.JsonHandleUtils;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 辅助类
 */

public class RetrofitHelper {
    private static String TAG = "RetrofitHelper";
    private long CONNECT_TIMEOUT = 5L;
    private long READ_TIMEOUT = 8L;
    private long WRITE_TIMEOUT = 30L;
    private static RetrofitHelper mInstance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance() {
        synchronized (RetrofitHelper.class) {
            if (mInstance == null) {
                mInstance = new RetrofitHelper();
            }
        }
        return mInstance;
    }

    private RetrofitHelper() {
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Contacts.DEV_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 获取OkHttpClient实例
     *
     * @return
     */

    private OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new RqInterceptor())
//                .addInterceptor(new RetryIntercepter())
                .addInterceptor(new ResponseInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .build();
        return okHttpClient;
    }

    /**
     * 请求拦截器
     */
    private class RqInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("uuid", DeviceUtils.getMacAddress())
                    .addHeader("AUTHORIZATION", AccountHelper.INSTANCE.getToken())
                    .addHeader("X-APP-TYPE", "android")
                    .build();
            return chain.proceed(request);
        }
    }


    /**
     * 添加返回结果统一处理拦截器
     */
    private class ResponseInterceptor implements Interceptor {

        @Override
        public Response intercept(final Chain chain) throws IOException {
            // 原始请求
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            String respString = source.buffer().clone().readString(Charset.defaultCharset());

            if(validate(respString)) {
                BaseResult result = new Gson().fromJson(respString, BaseResult.class);

                if (result != null && result.getCode() == 2) {
                    Log.d(TAG, "--->登录失效，自动重新登录");
                    Intent intent = new Intent(App.getAppContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    App.getAppContext().startActivity(intent);
                }
            }
            return response;

        }
    }

    private boolean validate(String jsonStr) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        if (!jsonElement.isJsonObject()) {
            return false;
        }
        return true;
    }

    /**
     * 日志拦截器
     */
    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间

            String method = request.method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i))
                                .append("=")
                                .append(body.encodedValue(i))
                                .append(",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    Log.d("---POST---",
                            String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                                    request.url(),
                                    chain.connection(),
                                    request.headers(),
                                    sb.toString()));
                }
            } else {
                Log.d("---GET---", String.format("发送请求 %s on %s%n%s",
                        request.url(),
                        chain.connection(),
                        request.headers()));
            }

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应事件

            ResponseBody responseBody = response.peekBody(1024 * 1024);//关键代码

            String responseString = JsonHandleUtils.jsonHandle(responseBody.string());
            LogUtils.d(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                    response.request().url(),
                    responseString,
                    (t2 - t1) / 1e6d,
                    response.headers()
            ));

            return response;
        }
    }


    /**
     * 重试拦截器
     */
    public class RetryIntercepter implements Interceptor {

        public int maxRetry;//最大重试次数
        private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

        public RetryIntercepter(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            System.out.println("retryNum=" + retryNum);
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                System.out.println("retryNum=" + retryNum);
                response = chain.proceed(request);
            }
            return response;
        }
    }


    public ApiService getServer() {
        return mRetrofit.create(ApiService.class);
    }
}
