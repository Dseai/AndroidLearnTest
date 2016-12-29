package com.syn.androidtest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syn.androidtest.R;
import com.syn.androidtest.adapter.TabLayoutFragmentAdapter2;
import com.syn.androidtest.fragment.subFragment.HomeFragment;
import com.syn.androidtest.fragment.subFragment.LikeFragment;
import com.syn.androidtest.fragment.subFragment.LocationFragment;
import com.syn.androidtest.fragment.subFragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙亚楠 on 2016/12/19.
 */
public class TabLayoutFragment2 extends Fragment implements TabLayout.OnTabSelectedListener{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView textView;
    private List<String> tabList=new ArrayList<>();
    private TabLayoutFragmentAdapter2 tabLayoutFragmentAdapter2;
    private int[] tabImages=new int[]{R.drawable.home,R.drawable.location,R.drawable.like,R.drawable.person};
    private List<Fragment> fragments=new ArrayList<>();

    public static TabLayoutFragment2 newInstance(String s){
        TabLayoutFragment2 tabLayoutFragment2=new TabLayoutFragment2();
        Bundle bundle=new Bundle();
        bundle.putString("args",s);
        tabLayoutFragment2.setArguments(bundle);
        return tabLayoutFragment2;
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
       resetTabIcon();
        int position=tab.getPosition();
        switch (position){
            case 0:
                tab.setIcon(R.drawable.home_fill);
                break;
            case 1:
                tab.setIcon(R.drawable.location_fill);
                break;
            case 2:
                tab.setIcon(R.drawable.like_fill);
                break;
            case 3:
                tab.setIcon(R.drawable.person_fill);
        }
    }

    private void resetTabIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.location);
        tabLayout.getTabAt(2).setIcon(R.drawable.like);
        tabLayout.getTabAt(3).setIcon(R.drawable.person);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tablayout,container,false);
        textView=(TextView)view.findViewById(R.id.activity_text_view);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String s=bundle.getString("args");
            if(!TextUtils.isEmpty(s)){
                textView.setText(s);
            }
        }
        viewPager=(ViewPager)view.findViewById(R.id.view_pager);
        tabLayout=(TabLayout)view.findViewById(R.id.tab_layout);
        initTabList();
        tabLayoutFragmentAdapter2 = new TabLayoutFragmentAdapter2(getChildFragmentManager(),tabList,getActivity(),fragments,tabImages);
        viewPager.setAdapter(tabLayoutFragmentAdapter2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addOnTabSelectedListener(this);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.location);
        tabLayout.getTabAt(2).setIcon(R.drawable.like);
        tabLayout.getTabAt(3).setIcon(R.drawable.person);

        return view;
    }

    private void initTabList() {
        tabList.clear();
        tabList.add("Home");
        tabList.add("Location");
        tabList.add("Like");
        tabList.add("Me");
    }

    @Override
    public void onStart(){
        super.onStart();
        initFragmentList();
    }

    private void initFragmentList() {
        fragments.clear();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(LocationFragment.newInstance("Location"));
        fragments.add(LikeFragment.newInstance("Like"));
        fragments.add(PersonFragment.newInstance("Me"));
    }
}
