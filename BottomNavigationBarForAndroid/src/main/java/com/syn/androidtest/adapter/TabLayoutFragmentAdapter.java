package com.syn.androidtest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syn.androidtest.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by 孙亚楠 on 2016/12/18.
 */
public class TabLayoutFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabList;
    private Context context;
   private List<Fragment> fragments;
    private ImageView tabIcon;
    private TextView tabText;
    private LinearLayout tabView;
    private int[] tabImages;
    public TabLayoutFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    public TabLayoutFragmentAdapter(FragmentManager fragmentManager,List<String> tabList, Context context,List<Fragment> fragments,int[] tabImages){
        super(fragmentManager);
        mTabList=tabList;
        this.context=context;
        this.fragments=fragments;
        this.tabImages=tabImages;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return mTabList.get(position);
    }
    public View getTabView(int position){
        View view= LayoutInflater.from(context).inflate(R.layout.layout_tab_view,null);
       tabView=(LinearLayout)view.findViewById(R.id.ll_tab_view);
        tabText=(TextView)view.findViewById(R.id.tv_tab_text);
        tabIcon=(ImageView)view.findViewById(R.id.iv_tab_icon);
        tabText.setText(mTabList.get(position));
        tabIcon.setImageResource(tabImages[position]);
        if (position==0){
            tabText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            tabIcon.setImageResource(R.drawable.home_fill);
        }
        return view;
    }
}
