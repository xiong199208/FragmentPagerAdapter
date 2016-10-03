package com.itheima.lazyviewpager.fragment;
/*
 *  @项目名：  LazyViewPager 
 *  @包名：    com.itheima.lazyviewpager.fragment
 *  @文件名:   Fragment01
 *  @创建者:   Administrator
 *  @创建时间: 十月
 *  @描述：    TODO
 */

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.lazyviewpager.base.BaseFragment;

public class Fragment04 extends BaseFragment {


    @Override
    protected View getFragmentView() {
        TextView tv = new TextView(getContext());
        tv.setText("我是Fragment04");
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);


        return tv;
    }
}

