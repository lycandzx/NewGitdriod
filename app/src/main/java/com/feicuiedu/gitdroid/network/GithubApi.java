package com.feicuiedu.gitdroid.network;

import com.feicuiedu.gitdroid.login.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2016/12/8.
 */

public interface GithubApi {
//    Client ID
//    79481a48832d8dc1c248
//    Client Secret
//    5d7ae5dcbf998eb748b6675f7ce9e3d3079c1dc8
    // 构建接口请求
    // 进行开发应用注册得到的应用信息
String  CLIENT_ID="79481a48832d8dc1c248";
String   CLIENT_SECRET="5d7ae5dcbf998eb748b6675f7ce9e3d3079c1dc8";
String   AUTH_SCOPE="user,public_repo,repo";
String CALL_BACK="lyc";
String AUTH_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+AUTH_SCOPE;
//@param clientId
//@param clientSecret
//    @param code
   @FormUrlEncoded
   @Headers("Accept: application/json")
   @POST("https://github.com/login/oauth/access_token")
    Call<AccessToken>getOAuthToken(
           @Field("client_id")String clientId,
           @Field("clientSecret")String clientSecret,
           @Field("code")String code);
@GET("/user")
Call<User>getUser();



}
