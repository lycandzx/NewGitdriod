package com.feicuiedu.gitdroid.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2016/12/8.
 */

public class AccessToken {
//    "access_token":"e72e16c7e42f292c6912e7710c838347ae178b4a", "scope":"repo,gist",
//            "token_type":"bearer"
//    String  access_token;
//    String  scope;
//    String  token_type;


    @SerializedName("access_token")
    private String accessToken;
    private String scope;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
