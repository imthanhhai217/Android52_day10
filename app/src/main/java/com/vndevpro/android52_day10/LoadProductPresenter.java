package com.vndevpro.android52_day10;

public class LoadProductPresenter implements ILoadProduct {

    private IListProductView iListProductView;

    public LoadProductPresenter(IListProductView iListProductView) {
        this.iListProductView = iListProductView;
    }

    public void getListProducts() {
        new LoadDataAsyncTask(this).execute(ConstantsApi.GET_LIST_PRODUCT);
    }
    public void getAllProducts() {
        new LoadDataAsyncTask(this).execute(ConstantsApi.GET_LIST_PRODUCT_ALL);
    }

    @Override
    public void onLoadProductSuccess(ListProductResponse response) {
        iListProductView.onGetListProductSuccess(response);
    }

    @Override
    public void onLoadProductFailed(String message) {
        iListProductView.onGetListProductFailed(message);
    }
}
