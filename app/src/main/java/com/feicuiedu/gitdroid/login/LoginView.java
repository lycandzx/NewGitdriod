package com.feicuiedu.gitdroid.login;

/**
 * Created by lenovo on 2016/12/8.
 */

public interface LoginView {
//    1. 显示一个进度动画
//    * 2. 显示信息：弹出吐司
//    * 3. 重新请求：请求失败了之后操作
//    * 4. 跳转页面
    void showProgress();
    void showMessage(String msg);
    void resetWeb();
    void navigationToMain();

}
