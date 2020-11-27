package com.example.framework;

import com.example.framework.view.LogingPage;

public interface IView {
    void onErroy(String message);
    void showsloading();
    void hideloading();
    void showErroy(String message);
    void showEmpty();
}
