package com.vndevpro.android52_day10;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadDataAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "LoadDataAsyncTask";
    private ILoadProduct iLoadProduct;

    public LoadDataAsyncTask(ILoadProduct iLoadProduct) {
        this.iLoadProduct = iLoadProduct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        String api = strings[0];
        try {
            URL url = new URL(api);
            InputStream is = url.openStream();
            InputStreamReader reader = new InputStreamReader(is);
            int data = reader.read();


            while (data != -1) {
                result += (char) data;
                data = reader.read();
            }
            Log.d(TAG, "doInBackground: " + result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!TextUtils.isEmpty(s) && !"".equals(s)) {
            Gson gson = new Gson();
            ListProductResponse listProductResponse = gson.fromJson(s, ListProductResponse.class);
            Log.d(TAG, "onPostExecute: " + listProductResponse.getProducts().get(0).toString());

            iLoadProduct.onLoadProductSuccess(listProductResponse);
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray productArray = jsonObject.getJSONArray("products");
//                JSONObject firstProduct = productArray.getJSONObject(0);
//                Log.d(TAG, "onPostExecute: " + firstProduct.toString());
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        } else {
            iLoadProduct.onLoadProductFailed("Load list product failed!");
        }
    }
}
