package com.itheima.lazyviewpager.view;
/*
 *  @项目名：  LazyViewPager 
 *  @包名：    com.itheima.lazyviewpager.view
 *  @文件名:   contentView
 *  @创建者:   Administrator
 *  @创建时间: 十月
 *  @描述：    TODO
 */

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.lazyviewpager.R;

public abstract class ContentView extends FrameLayout {
    Context mContext;

    Handler mHandler = new Handler();

    public ContentView(Context context) {
        super(context);

        mContext = context;


    }

    public ContentView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    /**
     * ViewPager的加载内容
     */
    public void initView() {

        removeAllViews();

        final View loading = View.inflate(mContext,R.layout.pager_loading,null);
        final View content = getChrildView();

        addView(loading);

        new Thread(){
            @Override
            public void run() {

                SystemClock.sleep(3000);


                mHandler.post(new Runnable() {
                    @Override
                     public void run() {
                        removeAllViews();
                        addView(content);
                    }
                });
            }
        }.start();
    }

    protected abstract View getChrildView();
}
