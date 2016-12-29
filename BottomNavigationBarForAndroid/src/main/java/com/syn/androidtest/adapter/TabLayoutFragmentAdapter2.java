package com.syn.androidtest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.List;

/**
 * Created by 孙亚楠 on 2016/12/19.
 */
public class TabLayoutFragmentAdapter2 extends FragmentPagerAdapter {
    private List<String> tabList;
    private Context context;
    private List<Fragment> fragments;
    private ImageView tabIcon;
    private TextView tabText;
    private int[] tabImage;
    private LinearLayout tabView;

    public TabLayoutFragmentAdapter2(FragmentManager fragmentManager,List<String> tabList,Context context,List<Fragment> fragments,int[] tabImage){
        super(fragmentManager);
        this.context=context;
        this.fragments=fragments;
        this.tabImage=tabImage;
        this.tabList=tabList;

    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabList.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return tabList.get(position);
    }
}
