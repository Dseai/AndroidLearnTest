package com.syn.androidtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.syn.androidtest.R;
import com.syn.androidtest.adapter.TabLayoutFragmentAdapter;
import com.syn.androidtest.fragment.subFragment.HomeFragment;
import com.syn.androidtest.fragment.subFragment.LikeFragment;
import com.syn.androidtest.fragment.subFragment.LocationFragment;
import com.syn.androidtest.fragment.subFragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙亚楠 on 2016/12/18.
 */
public class TabLayoutFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView textView;
    private List<String> mTabList=new ArrayList<>();
    private TabLayoutFragmentAdapter adapter;
    private int[] mTabImgs=new int[]{R.drawable.home,R.drawable.location,R.drawable.like,R.drawable.person};
    private List<Fragment> fragments=new ArrayList<>();

    public static TabLayoutFragment newInstance(String s) {
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("args", s);
        tabLayoutFragment.setArguments(bundle);
        return tabLayoutFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tablayout,container,false);
        textView = (TextView) view.findViewById(R.id.activity_text_view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString("args");
            if (!TextUtils.isEmpty(s)) {
                textView.setText(s);
            }
        }
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        initTabList();
        adapter = new TabLayoutFragmentAdapter(getChildFragmentManager(), mTabList, getActivity(), fragments, mTabImgs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(adapter.getTabView(i));
        }
        tabLayout.addOnTabSelectedListener(this);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        initFragementList();
    }

    private void initFragementList() {
        fragments.clear();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(LocationFragment.newInstance("Location"));
        fragments.add(LikeFragment.newInstance("Like"));
        fragments.add(PersonFragment.newInstance("Me"));
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add("Home");
        mTabList.add("Location");
        mTabList.add("Like");
        mTabList.add("Me");
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void setTabSelectedState(TabLayout.Tab tab) {
        View constomeView=tab.getCustomView();
        TextView tabText=(TextView)constomeView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon=(ImageView)constomeView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        String s=tabText.getText().toString();
        if ("Home".equals(s)){
            tabIcon.setImageResource(R.drawable.home_fill);
        }else if ("Location".equals(s)){
            tabIcon.setImageResource(R.drawable.location_fill);
        }else if ("Like".equals(s)){
            tabIcon.setImageResource(R.drawable.like_fill);
        }else if ("Me".equals(s)) {
            tabIcon.setImageResource(R.drawable.person_fill);
        }
    }
    public  void setTabUnSelectedState(TabLayout.Tab tab){
        View constomeView=tab.getCustomView();
        TextView tabText=(TextView)constomeView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon=(ImageView)constomeView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        String s=tabText.getText().toString();
        if ("Home".equals(s)){
            tabIcon.setImageResource(R.drawable.home_fill);
        }else if ("Location".equals(s)){
            tabIcon.setImageResource(R.drawable.location_fill);
        }else if ("Like".equals(s)){
            tabIcon.setImageResource(R.drawable.like_fill);
        }else if ("Me".equals(s)) {
            tabIcon.setImageResource(R.drawable.person_fill);
        }

    }
}


