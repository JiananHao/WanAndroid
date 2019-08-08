package com.example.wanandroid.model.api;

import com.example.wanandroid.model.bean.BannerBean;
import com.example.wanandroid.model.bean.HomeArticleBean;
import com.example.wanandroid.model.bean.KnowledgeBean;
import com.example.wanandroid.model.bean.PlayBean;
import com.example.wanandroid.model.bean.ProjectBean;
import com.example.wanandroid.model.bean.ProjectItemBean;
import com.example.wanandroid.model.bean.UserBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @Headers({"baseUrl:normal"})
    @GET("article/list/{page}/json")
    Observable<HomeArticleBean> getHomelist(@Path("page") int num);

    @Headers({"baseUrl:normal"})
    @GET("banner/json")
    Observable<BannerBean> getBanner();

    @Headers({"baseUrl:normal"})
    @GET("tree/json")
    Observable<KnowledgeBean> getTree();

    @Headers({"baseUrl:normal"})
    @GET("navi/json")
    Observable<PlayBean> getNavi();

    @Headers({"baseUrl:normal"})
    @GET("project/tree/json")
    Observable<ProjectBean> getProject();

    @Headers({"baseUrl:normal"})
    @GET("project/list/{page}/json")
    Observable<ProjectItemBean> getProjectItem(@Path("page") int num, @Query("cid")int id);

    @Headers({"baseUrl:normal"})
    @POST("user/login")
    @FormUrlEncoded
    Observable<UserBean> login(@Field("username") String username, @Field("password")String password);

    @Headers({"baseUrl:normal"})
    @POST("user/register")
    @FormUrlEncoded
    Observable<UserBean> register(@Field("username") String username, @Field("password")String password, @Field("repassword")String repassword);
}
