package com.mk.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.latte.delegates.LatteDelegate;
import com.mk.latte.ec.R;
import com.mk.latte.ec.R2;

import butterknife.BindView;

/**
 * Created by Aatoken on 2018/5/3.
 */

public class GoodsInfoDelegate extends LatteDelegate {


    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;

    private static final String ARG_GOODS_DATA="arg_goods_data";
    private JSONObject mData=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    public static GoodsInfoDelegate create(@NonNull String goodsInfo) {
        final Bundle args=new Bundle();
        args.putString(ARG_GOODS_DATA,goodsInfo);
        final GoodsInfoDelegate delegate=new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        final String goodsData = args.getString(ARG_GOODS_DATA);
        mData = JSON.parseObject(goodsData);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");
        mGoodsInfoTitle.setText(name);
        mGoodsInfoDesc.setText(desc);
        mGoodsInfoPrice.setText(String.valueOf(price));
    }




}
