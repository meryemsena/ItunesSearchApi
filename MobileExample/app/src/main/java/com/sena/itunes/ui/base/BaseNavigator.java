package com.sena.itunes.ui.base;

public interface BaseNavigator {

    void handleError(Throwable throwable);

    void finishView();

    void finishActivity();

    void showMessage(String text);

}
