package com.vndevpro.android52_day10;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IListProductView,IItemClickListener {

    private static final String TAG = "MainActivity";
    private LoadProductPresenter mLoadProductPresenter;
    private RecyclerView rvProduct;
    private LinearLayout llLoading;
    private ArrayList<Product> mListProducts;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        llLoading.setVisibility(View.VISIBLE);
        mLoadProductPresenter.getListProducts();
    }

    private void initView() {
        rvProduct = findViewById(R.id.rvProduct);
        llLoading = findViewById(R.id.llLoading);
        mLoadProductPresenter = new LoadProductPresenter(this);
    }

    private void bindDataRc(ListProductResponse response) {
        rvProduct.setVisibility(View.VISIBLE);
        mProductAdapter = new ProductAdapter((ArrayList<Product>) response.getProducts());
        mProductAdapter.setCallback(this);
        rvProduct.setAdapter(mProductAdapter);
    }

    @Override
    public void onGetListProductSuccess(ListProductResponse response) {
        llLoading.setVisibility(View.GONE);
        bindDataRc(response);
    }

    @Override
    public void onGetListProductFailed(String message) {
        rvProduct.setVisibility(View.VISIBLE);
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
        rvProduct.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onChangeWishList(int position) {

    }

    @Override
    public void onDelete(int position) {

    }

    @Override
    public void onUpdate(int position) {

    }
}