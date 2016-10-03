package com.itheima.lazyviewpager.manager;
/*
 *  @项目名：  LazyViewPager 
 *  @包名：    com.itheima.lazyviewpager.manager
 *  @文件名:   FragmentFactory
 *  @创建者:   Administrator
 *  @创建时间: 十月
 *  @描述：    TODO
 */

import android.util.Log;

import com.itheima.lazyviewpager.base.BaseFragment;
import com.itheima.lazyviewpager.fragment.Fragment01;
import com.itheima.lazyviewpager.fragment.Fragment02;
import com.itheima.lazyviewpager.fragment.Fragment03;
import com.itheima.lazyviewpager.fragment.Fragment04;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {
    //维护一个Fragment的集合
    private Map<Integer,BaseFragment> mCaches = new HashMap<>();
    //设计为单例模式
    private static FragmentFactory sInstance;

    private FragmentFactory(){}

    public static FragmentFactory getInstance(){
        if(sInstance == null){
            sInstance = new FragmentFactory();
        }
        return sInstance;
    }

    public BaseFragment getFragment(int position){
        //做一个集合记录下来所有已经加载过的fragment，下一次直接取出，这样保证了唯一性，模拟了FragmentManager的内存缓存
        BaseFragment fragment;
        if(mCaches.containsKey(position)){
            //上一次已保存的情况，取出来即可
            fragment = mCaches.get(position);
            Log.e("fragment", "取出fragment");
            return fragment;
        }
        switch (position){
            case 0:
                fragment = new Fragment01();
                break;
            case 1:
                fragment = new Fragment02();
                break;
            case 2:
                fragment = new Fragment03();
                break;
            case 3:
                fragment = new Fragment04();
                break;

            default:
                fragment = new Fragment01();
                break;
        }
        mCaches.put(position, fragment);
        Log.e("fragment", "保存fragment");
        return fragment;
    }
}
