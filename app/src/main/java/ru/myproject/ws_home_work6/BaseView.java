package ru.myproject.ws_home_work6;

public interface BaseView {
    void showToast(String message);
    void showToast(String message, int length);
    void showLoadingDialog(String message);
    void dismissLoadingDialog();
    void showSnackBar(String message);
}
