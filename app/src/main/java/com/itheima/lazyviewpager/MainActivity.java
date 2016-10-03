package com.itheima.lazyviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.itheima.lazyviewpager.base.BaseFragment;
import com.itheima.lazyviewpager.manager.FragmentFactory;

import org.itheima.tabindicator.library.TabIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mPager;

    public String[] title = {"fragment01","fragment02","fragment03","fragment04"};
    //维护一个已经加载了的Fragment集合
    List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.viewpager);
        //ViewPager的指示器,用的是开源框架，为了方便测试- -；
        TabIndicator indicator = (TabIndicator) findViewById(R.id.indicator);


        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        //指示器绑定ViewPager
        indicator.setViewPager(mPager);

        //设置ViewPager监听，显示出当前页时，再加载数据
        mPager.addOnPageChangeListener(this);

    }

    Boolean first = true;

   //显示首页时，onPageSelected不会被调用，需要手动调用它
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (first && positionOffset == 0 && positionOffsetPixels == 0){
            onPageSelected(0);
            first = false;
        }
    }

    @Override
    public void onPageSelected(int position) {

        /*BaseFragment fragment = mFragments.get(position);
        fragment.loadData();*/

        //直接可以通过FragmentFactory拿到已经加载过的Fragment对象
        BaseFragment fragment = FragmentFactory.getInstance().getFragment(position);
        fragment.loadData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {



    }

    /**
     * 通过Fragment工厂来实现FragmentStatePagerAdapter对Fragment的复用
     */
    class MyPagerAdapter extends FragmentStatePagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            BaseFragment fragment = FragmentFactory.getInstance().getFragment(position);

            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    /**
     * FragmentPagerAdapter的使用范例
     *//*
    class MyPagerAdapter extends FragmentPagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //将已加载的fragment用集合存起来
            switch (position) {
                case 0:
                    BaseFragment fragment01 = new Fragment01();
                    mFragments.add(fragment01);
                    System.out.println("Fragment01对应的getItem");
                    return fragment01;

                case 1:
                    BaseFragment fragment02 = new Fragment02();
                    mFragments.add(fragment02);
                    System.out.println("Fragment02对应的getItem");
                    return fragment02;

                case 2:
                    BaseFragment fragment03 = new Fragment03();
                    mFragments.add(fragment03);
                    System.out.println("Fragment03对应的getItem");
                    return fragment03;

                case 3:
                    BaseFragment fragment04 = new Fragment04();
                    mFragments.add(fragment04);
                    System.out.println("Fragment04对应的getItem");
                    return fragment04;

                default:
                    BaseFragment fragment = new Fragment01();
                    mFragments.add(fragment);
                    System.out.println("Fragment01对应的getItem");
                    return fragment;

            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }*/
}
