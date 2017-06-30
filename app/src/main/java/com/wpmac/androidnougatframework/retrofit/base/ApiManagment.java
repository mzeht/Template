package com.wpmac.androidnougatframework.retrofit.base;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wpmac.androidnougatframework.BuildConfig;
import com.wpmac.androidnougatframework.retrofit.DoubanApi;
import com.wpmac.androidnougatframework.retrofit.GankApi;
import com.wpmac.androidnougatframework.retrofit.TingApi;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:31
 * @email: mzeht8303@gamil.com
 */
public class ApiManagment {

    private static ApiManagment instance;
    private Gson gson;
    public static String BASE_URL = "http://222.222.222.222:8080";
    // gankio、豆瓣、（轮播图）
    private final static String API_GANKIO = "https://gank.io/api/";
    private final static String API_DOUBAN = "Https://api.douban.com/";
    private final static String API_TING = "https://tingapi.ting.baidu.com/v1/restserver/";
    private static GankApi mGankApi;
    private static TingApi mTingApi;
    private static DoubanApi mDoubanApi;
    public  GankApi getGankApi() {

        if (mGankApi == null) {
            synchronized (ApiManagment.class) {
                if (mGankApi == null) {
                    mGankApi = getBuilder(API_GANKIO).build().create(GankApi.class);
                }
            }
        }
        return mGankApi;
    }
    public TingApi getTingApi(){
        if (mTingApi == null) {
            synchronized (ApiManagment.class) {
                if (mTingApi == null) {
                    mTingApi = getBuilder(API_TING).build().create(TingApi.class);
                }
            }
        }
        return mTingApi;
    }

    public DoubanApi getDoubanApi(){
        if (mDoubanApi == null) {
            synchronized (ApiManagment.class) {
                if (mDoubanApi == null) {
                    mDoubanApi = getBuilder(API_DOUBAN).build().create(DoubanApi.class);
                }
            }
        }
        return mDoubanApi;
    }





    public static ApiManagment getInstance() {
        if (instance == null) {
            synchronized (ApiManagment.class) {
                if (instance == null) {
                    instance = new ApiManagment();
                }
            }
        }
        return instance;
    }


    private Retrofit.Builder getBuilder(String apiUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(getOkClient())
                .baseUrl(apiUrl)//设置远程地址
//        builder.addConverterFactory(new NullOnEmptyConverterFactory());
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(getGson()));
        return builder;
    }


        private Gson getGson() {
            if (gson == null) {
                GsonBuilder builder = new GsonBuilder();
                builder.setLenient();
                builder.setFieldNamingStrategy(new AnnotateNaming());
                builder.serializeNulls();
                gson = builder.create();
            }
            return gson;
        }

    public OkHttpClient getOkClient() {
        OkHttpClient client1;
        client1 = getUnsafeOkHttpClient();
        return client1;
    }

    private static class AnnotateNaming implements FieldNamingStrategy {
        @Override
        public String translateName(Field field) {
            ParamNames a = field.getAnnotation(ParamNames.class);
            return a != null ? a.value() : FieldNamingPolicy.IDENTITY.translateName(field);
        }
    }

    private OkHttpClient getUnsafeOkHttpClient() {

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }};
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);
            okBuilder.addInterceptor(new HttpHeadInterceptor());
            okBuilder.addInterceptor(getInterceptor());
            okBuilder.sslSocketFactory(sslSocketFactory);
            okBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
//                    Log.d("ApiManagment", "==come");
                    return true;
                }
            });

            return okBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Interceptor getInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // 测试
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE); // 打包
        }
        return interceptor;
    }

//
//    private ApiManagment() {
//
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//        builder.connectTimeout(5, TimeUnit.SECONDS)
//                .writeTimeout(5, TimeUnit.SECONDS)
//                .readTimeout(5,TimeUnit.SECONDS)
//                .addNetworkInterceptor(new StethoInterceptor());
////        .addInterceptor(new Interceptor() {
////            @Override
////            public Response intercept(Chain chain) throws IOException {
//////                L.d("Interceptor");
////
////                // Request customization: add request headers
////                String Nonce = String.valueOf(System.currentTimeMillis());
////                String CheckSum = EncryptUtils.encryptSHA1ToString("hehehehheheheheheheh"+Nonce);
////                Request.Builder requestBuilder = chain.request().newBuilder()
////                        .addHeader("AppKey", "hehehehhehehe")
////                        .addHeader("Nonce", Nonce)
////                        .addHeader("CheckSum", CheckSum)
//////                        .addHeader("x-session-token", BasePreference.getInstance().getToken())
////                        .addHeader("AppSecret", "ehhehehehehehhehehehehheh");
////
////                Request request = requestBuilder.build();
////                return chain.proceed(request);
////            }
////        });
//        if (BuildConfig.DEBUG) {
//            // Log信息拦截器
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            //设置 Debug Log 模式
//            builder.addInterceptor(loggingInterceptor);
//        }
//
//        OkHttpClient client = builder.build();
//
//        Retrofit retrofit_gank = new Retrofit.Builder()
//                .baseUrl(API_GANKIO)
//                .client(client)
//                .addConverterFactory(CustomGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        mGankApi = retrofit_gank.create(GankApi.class);
//
//
//        Retrofit retrofit_douban = new Retrofit.Builder()
//                .baseUrl(API_DOUBAN)
//                .client(client)
//                .addConverterFactory(CustomGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        mTingApi = retrofit_douban.create(TingApi.class);
//
//        Retrofit retrofit_ = new Retrofit.Builder()
//                .baseUrl(API_TING)
//                .client(client)
//                .addConverterFactory(CustomGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        mTingApi = retrofit_douban.create(TingApi.class);
//
//
//
//    }
}
