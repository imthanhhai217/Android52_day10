package com.vndevpro.android52_day10;

public interface IListProductView {
    void onGetListProductSuccess(ListProductResponse response);

    void onGetListProductFailed(String message);
}
