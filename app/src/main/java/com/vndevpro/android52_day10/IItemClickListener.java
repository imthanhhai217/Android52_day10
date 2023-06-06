package com.vndevpro.android52_day10;

public interface IItemClickListener {
    void onItemClick(int position);

    void onChangeWishList(int position);

    void onDelete(int position);

    void onUpdate(int position);
}
