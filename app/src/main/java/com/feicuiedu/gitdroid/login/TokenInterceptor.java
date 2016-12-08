package com.feicuiedu.gitdroid.login;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2016/12/8.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取拦截的请求
        Request request = chain.request();
        // 往请求里面添加头信息
        Request.Builder builder = request.newBuilder();
        if (UserRepo.hasAccessToken()) {
            builder.header("Authorization", "token " + UserRepo.getAccessToken());
        }
        Response response = chain.proceed(builder.build());
        if (response.isSuccessful()) {
            return response;
        }
        if (response.code() == 401 || response.code() == 403) {
            throw new IOException("未经授权的！");
        } else {
            throw new IOException("响应码：" + response.code());
        }
    }
}
