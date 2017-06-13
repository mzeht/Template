package com.wpmac.androidnougatframework.retrofit.base;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wpmac.androidnougatframework.BuildConfig;
import com.wpmac.androidnougatframework.retrofit.MzehtApi;
import com.wpmac.androidnougatframework.utils.EncryptUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:31
 * @email: mzeht8303@gamil.com
 */
public class ApiRetrofit {
    public static String BASE_URL = "http://222.222.222.222:8080";
    private MzehtApi mMzehtApi;

    public MzehtApi getSmartApi() {
        return mMzehtApi;
    }



    ApiRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
        .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
//                L.d("Interceptor");

                // Request customization: add request headers
                String Nonce = String.valueOf(System.currentTimeMillis());
                String CheckSum = EncryptUtils.encryptSHA1ToString("hehehehheheheheheheh"+Nonce);
                Request.Builder requestBuilder = chain.request().newBuilder()
                        .addHeader("AppKey", "hehehehhehehe")
                        .addHeader("Nonce", Nonce)
                        .addHeader("CheckSum", CheckSum)
//                        .addHeader("x-session-token", BasePreference.getInstance().getToken())
                        .addHeader("AppSecret", "ehhehehehehehhehehehehheh");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        Retrofit retrofit_baimi = new Retrofit.Builder()
                .baseUrl(BASE_URL+"/")
                .client(client)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mMzehtApi = retrofit_baimi.create(MzehtApi.class);

    }
}
