package com.feicuiedu.gitdroid.network;


import com.feicuiedu.gitdroid.login.TokenInterceptor;
import com.feicuiedu.gitdroid.login.User;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by lenovo on 2016/12/8.
 */
public class GithubClient implements GithubApi{
    private static GithubClient mGithubClient;
    private final GithubApi mGithubApi;

    public static synchronized GithubClient getInstance() {

        if (mGithubClient == null) {
            mGithubClient = new GithubClient();


        }


        return mGithubClient;
    }

    private GithubClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new TokenInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        mGithubApi = retrofit.create(GithubApi.class);


    }


    @Override
    public Call<AccessToken> getOAuthToken(@Field("client_id") String clientId, @Field("clientSecret") String clientSecret, @Field("code") String code) {
        return mGithubApi.getOAuthToken(clientId,clientSecret,code);
    }

    @Override
    public Call<User> getUser() {
        return mGithubApi.getUser();
    }
}
