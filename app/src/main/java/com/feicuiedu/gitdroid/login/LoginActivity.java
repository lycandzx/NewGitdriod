package com.feicuiedu.gitdroid.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feicuiedu.gitdroid.MainActivity;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.commons.LogUtils;
import com.feicuiedu.gitdroid.network.AccessToken;
import com.feicuiedu.gitdroid.network.GithubApi;
import com.feicuiedu.gitdroid.network.GithubClient;


import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements  LoginView{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.gifImageView)
    GifImageView mGifImageView;
    private com.feicuiedu.gitdroid.login.LoginPresenter mLoginPresenter;
    private ActivityUtils mActivityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
       mActivityUtils=new ActivityUtils(this);
       mLoginPresenter=new com.feicuiedu.gitdroid.login.LoginPresenter(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWebView();
    }

    private void initWebView() {
//// 为了删除所有的信息，删除登录信息
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        // 加载登录页面
        mWebView.loadUrl(GithubApi.AUTH_URL);
        // 为了让WebView获取焦点
        mWebView.setFocusable(true);
        mWebView.setFocusableInTouchMode(true);
        // 设置WebView加载的进度，动画的展示和隐藏
        mWebView.setWebChromeClient(mWebChromeClient);
        // 设置WebView的刷新(重定向)
        mWebView.setWebViewClient(mWebViewClient);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    页面刷新
            Uri uri = Uri.parse(url);
            if (GithubApi.CALL_BACK.equals(uri.getScheme())) {
                String code = uri.getQueryParameter("code");
//        得到code值，获取token
                Log.e("ee", "临时的授权码" + code);
//  临时的授权码3a1f3df2b8729d3bb484
                mLoginPresenter.login(code);

                return true;
            }

            return super.shouldOverrideUrlLoading(view, url);

        }
    };


    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 页面加载出来之后，动画隐藏
            if (newProgress >= 100) {
                mGifImageView.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void showProgress() {
        mGifImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {
        mActivityUtils.showToast(msg);
    }

    @Override
    public void resetWeb() {
        initWebView();
    }

    @Override
    public void navigationToMain() {
        mActivityUtils.startActivity(MainActivity.class);
        finish();
    }



}

