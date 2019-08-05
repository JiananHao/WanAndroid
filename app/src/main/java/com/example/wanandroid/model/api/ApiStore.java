package com.example.wanandroid.model.api;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.wanandroid.base.MyApplication;
import com.example.wanandroid.model.constant.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiStore {

    private static Retrofit retrofit;

    static {
        createProxy();
    }
    public static <T> T createApi(Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }

    public static void createProxy(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();

        //设置缓存目录
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(),"cache");
        //100M
        Cache cache = new Cache(cacheFile,1024*1024*100);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (NetworkUtil.isNetworkConnected(MyApplication.getInstance())){
                    Response response = chain.proceed(request);
                    int maxAge = 10;
                    String cacheControl = request.cacheControl().toString();
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .addHeader("Cache-Control","public, max-age ="+ maxAge)
                            .build();
                }else {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Response response = chain.proceed(request);
                    int maxStale = 60 * 60 * 24 * 3;
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .addHeader("Cache-Control","public, only-if-cached ,max-stale ="+ maxStale)
                            .build();
                }
            }
        };
        //配置OKHttp
        OkHttpClient builder = new OkHttpClient().newBuilder()
                .sslSocketFactory(getSSLSocketFactory())
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder)
                .build();
    }

    @NonNull
    @SuppressLint("TrustAllX509TrustManager")
    private static SSLSocketFactory getSSLSocketFactory() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    try {
                        chain[0].checkValidity();
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }};
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            return null;
        }
    }
}
