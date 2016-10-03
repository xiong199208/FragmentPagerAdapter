package com.itheima.lazyviewpager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.lazyviewpager.view.ContentView;

/*
 *  @项目名：  LazyViewPager 
 *  @包名：    com.itheima.lazyviewpager.base
 *  @文件名:   BaseFragment
 *  @创建者:   Administrator
 *  @创建时间: 十月
 *  @描述：    TODO
 */

public abstract class BaseFragment extends Fragment {

    private ContentView mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        System.out.println("onCreateView");
        //防止不断创建新的视图对象
        if (mView==null){
            mView = new ContentView(getContext()) {
                @Override
                protected View getChrildView() {
                    return getFragmentView();
                }
            };
        }
        return mView;
    }

    /**
     * 子类来负责填充自己的视图
     * @return
     */
    protected abstract View getFragmentView();

    /**
     * 主activity的onCreate()执行完后，该方法才执行
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mView.initView();
        System.out.println("onActivityCreated");
    }

    /**
     * 加载视图
     */
    public void loadData() {
        mView.initView();
    }

}
