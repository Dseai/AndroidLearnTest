package com.syn.androidtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syn.androidtest.R;
import com.syn.androidtest.fragment.subFragment.HomeFragment;
import com.syn.androidtest.fragment.subFragment.LikeFragment;
import com.syn.androidtest.fragment.subFragment.LocationFragment;
import com.syn.androidtest.fragment.subFragment.PersonFragment;

/**
 * Created by 孙亚楠 on 2016/12/18.
 */
public class TextTabFragment  extends Fragment implements View.OnClickListener{
    private TextView tvHome,tvLocation,tvLike,tvMe,textView;
    private HomeFragment homeFragment;
    private LikeFragment likeFragment;
    private LocationFragment locationFragment;
    private PersonFragment personFragment;

    public static TextTabFragment newInstance(String s){
        TextTabFragment viewPagerFragment=new TextTabFragment();
        Bundle bundle=new Bundle();
        bundle.putString("args",s);
        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_text_tab,container,false);
        textView=(TextView)view.findViewById(R.id.activity_text_view);
        tvHome=(TextView)view.findViewById(R.id.tv_home);
        tvLike=(TextView)view.findViewById(R.id.tv_like);
        tvLocation=(TextView)view.findViewById(R.id.tv_location);
        tvMe=(TextView)view.findViewById(R.id.tv_person);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String s=bundle.getString("args");
            if (!TextUtils.isEmpty(s)){
                textView.setText(s);
            }
        }
        tvLike.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        setDefaultFragment();
        return view;
    }

    @Override
    public void onClick(View view) {
        resetTabState();
        switch (view.getId()){
            case R.id.tv_home:
                setTabState(tvHome,R.drawable.home_fill,getColor(R.color.colorPrimary));
                switchFragment(0);
                break;
            case R.id.tv_location:
                setTabState(tvLocation,R.drawable.location_fill,getColor(R.color.colorPrimary));
                switchFragment(1);
                break;
            case R.id.tv_like:
                setTabState(tvLike,R.drawable.like_fill,getColor(R.color.colorPrimary));
                switchFragment(2);
                break;
            case R.id.tv_person:
                setTabState(tvMe,R.drawable.person_fill,getColor(R.color.colorPrimary));
                switchFragment(3);
                break;
        }

    }

    private void resetTabState() {
        setTabState(tvHome,R.drawable.home,getColor(R.color.black_1));
        setTabState(tvLocation,R.drawable.location,getColor(R.color.black_1));
        setTabState(tvLike,R.drawable.like,getColor(R.color.black_1));
        setTabState(tvMe,R.drawable.person,getColor(R.color.black_1));
    }

    private void switchFragment(int i) {
        FragmentTransaction transaction =getChildFragmentManager().beginTransaction();
        switch (i){
            case 0:
                if(homeFragment==null){
                    homeFragment=HomeFragment.newInstance("Home");

                }transaction.replace(R.id.sub_content,homeFragment);
                break;
            case 1:
                if(locationFragment==null){
                    locationFragment=LocationFragment.newInstance("Location");

                }transaction.replace(R.id.sub_content,locationFragment);
                break;
            case 2:
                if(likeFragment==null){
                    likeFragment=LikeFragment.newInstance("Like");

                }transaction.replace(R.id.sub_content,likeFragment);
                break;
            case 3:
                if(personFragment==null){
                    personFragment=PersonFragment.newInstance("Person");

                }transaction.replace(R.id.sub_content,personFragment);
                break;

        }
        transaction.commit();
    }

    private void setTabState(TextView textView,int image,int color) {
        //API至少17才能调用
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,image,0,0);
        textView.setTextColor(color);
    }



    private void setDefaultFragment() {
        switchFragment(0);
        setTabState(tvHome,R.drawable.home_fill,getColor(R.color.colorPrimary));
    }

    public int getColor(int i) {
        return ContextCompat.getColor(getActivity(),i);
    }
}
