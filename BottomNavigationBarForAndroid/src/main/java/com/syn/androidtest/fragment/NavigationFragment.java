package com.syn.androidtest.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.syn.androidtest.R;
import com.syn.androidtest.fragment.subFragment.HomeFragment;
import com.syn.androidtest.fragment.subFragment.LikeFragment;
import com.syn.androidtest.fragment.subFragment.LocationFragment;
import com.syn.androidtest.fragment.subFragment.PersonFragment;

/**
 * Created by 孙亚楠 on 2016/12/14.
 */
public class NavigationFragment extends Fragment implements BottomNavigationBar.OnTabSelectedListener{
  private BottomNavigationBar bottomNavigationBar;
    private HomeFragment homeFragment;
    private LocationFragment locationFragment;
    private LikeFragment likeFragment;
    private PersonFragment personFragment;
    private TextView textView;

    public static NavigationFragment newInstance(String s){
        NavigationFragment navigationFragment=new NavigationFragment();
        Bundle bundle=new Bundle();
        bundle.putString("args",s);
        navigationFragment.setArguments(bundle);
        return navigationFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_bottom_navigation_bar,container,false);
        textView=(TextView) view.findViewById(R.id.activity_text_view);
        Bundle bundle=getArguments();
        if (bundle!=null){
            String s=bundle.getString("args");
            if(!TextUtils.isEmpty(s)){
                textView.setText(s);
            }
        }
        bottomNavigationBar=(BottomNavigationBar)view.findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //添加四个导航键
        /**
         * BottomNavigationItem(图标，名称)
         * setInactiveIconResource 活动图标位置
         */
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home_fill, "Home").setInactiveIconResource(R.drawable.home).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.location_fill, "Location").setInactiveIconResource(R.drawable.location).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.like_fill, "Like").setInactiveIconResource(R.drawable.like).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.person_fill, "Person").setInactiveIconResource(R.drawable.person).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();
         bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
        return view;

    }
//设置默认的fragment 为homefragment
    private void setDefaultFragment() {
        FragmentTransaction transction=getFragmentManager().beginTransaction();
        HomeFragment homeFragment= HomeFragment.newInstance("Home");
        transction.replace(R.id.sub_content,homeFragment).commit();

    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction beginTransaction=getFragmentManager().beginTransaction();
        switch (position){
            case 0:
                if(homeFragment==null){
                    homeFragment=HomeFragment.newInstance("Home");

                }beginTransaction.replace(R.id.sub_content,homeFragment);
                break;
            case 1:
                if(locationFragment==null){
                    locationFragment=LocationFragment.newInstance("Location");

                }beginTransaction.replace(R.id.sub_content,locationFragment);
                break;
            case 2:
                if(likeFragment==null){
                    likeFragment=LikeFragment.newInstance("Like");
                }beginTransaction.replace(R.id.sub_content,likeFragment);
                break;
            case 3:
                if(personFragment==null){
                    personFragment=PersonFragment.newInstance("Person");
                }beginTransaction.replace(R.id.sub_content,personFragment);
                break;
        }
beginTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
