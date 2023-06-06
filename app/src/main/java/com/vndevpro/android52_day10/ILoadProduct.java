package com.vndevpro.android52_day10;

public interface ILoadProduct {
    void onLoadProductSuccess(ListProductResponse response);

    void onLoadProductFailed(String message);
}
