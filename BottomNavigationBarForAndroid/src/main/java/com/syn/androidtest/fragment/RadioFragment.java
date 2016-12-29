package com.syn.androidtest.fragment;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.syn.androidtest.R;
import com.syn.androidtest.fragment.subFragment.HomeFragment;
import com.syn.androidtest.fragment.subFragment.LikeFragment;
import com.syn.androidtest.fragment.subFragment.LocationFragment;
import com.syn.androidtest.fragment.subFragment.PersonFragment;

/**
 * Created by 孙亚楠 on 2016/12/18.
 */
public class RadioFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private TextView textView;
    private RadioGroup radioGroup;
    private HomeFragment homeFragment;
    private LocationFragment locationFragment;
    private LikeFragment likeFragment;
    private PersonFragment personFragment;
    private RadioButton RadioHome,RadioLocation,RadioLike,RadioMe;

     public static RadioFragment newInstance(String s){
         RadioFragment radioFragment=new RadioFragment();
         Bundle bundle=new Bundle();
         bundle.putString("args",s);
         radioFragment.setArguments(bundle);
         return radioFragment;

     }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_radio_group,container,false);
        textView=(TextView)view.findViewById(R.id.activity_text_view);
        radioGroup=(RadioGroup)view.findViewById(R.id.radio_group);
        RadioHome=(RadioButton)view.findViewById(R.id.rb_home);
        RadioLocation=(RadioButton)view.findViewById(R.id.rb_location);
        RadioLike=(RadioButton)view.findViewById(R.id.rb_like);
        RadioMe=(RadioButton)view.findViewById(R.id.rb_person);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String s=bundle.getString("args");
            if(!TextUtils.isEmpty(s)){
                textView.setText(s);
            }
        }
        radioGroup.setOnCheckedChangeListener(this);

        return view;
    }
    @Override
    public void onStart(){
        setDefaultFragment();//设置默认显示第一个home
        super.onStart();
    }
    private void setDefaultFragment() {
        RadioHome.setChecked(true);
        RadioLike.setChecked(false);
        RadioLocation.setChecked(false);
        RadioMe.setChecked(false);
        if(RadioHome.isChecked()){
            setTabState();
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            homeFragment=HomeFragment.newInstance("Home");
            transaction.replace(R.id.sub_content,homeFragment).commit();

        }

    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        switch (i){
            case R.id.rb_home:
                if(homeFragment==null){
                    homeFragment=HomeFragment.newInstance("Home");
                }
                transaction.replace(R.id.sub_content,homeFragment);
                break;
            case R.id.rb_like:
                if (likeFragment==null){
                    likeFragment=LikeFragment.newInstance("Like");
                }
                transaction.replace(R.id.sub_content,likeFragment);
                break;
            case R.id.rb_location:
                if(locationFragment==null){
                    locationFragment=LocationFragment.newInstance("Location");
                }
                transaction.replace(R.id.sub_content,locationFragment);
                break;
            case R.id.rb_person:
                if(personFragment==null){
                    personFragment=PersonFragment.newInstance("Person");
                }
                transaction.replace(R.id.sub_content,personFragment);
                break;
        }
        setTabState();
        transaction.commit();

    }
    /**
     * 设置每个tab的选中和没被选中的样式
     */
    private void setTabState() {
        setHomeState();
        setLocationState();
        setLikeState();
        setMeState();
    }

    private void setLikeState() {
        if(RadioLike.isChecked()){
            RadioLike.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        }else {
            RadioLike.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        }
    }

    private void setMeState() {
        if(RadioMe.isChecked()){
            RadioMe.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        }else {
            RadioMe.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        }
    }

    private void setLocationState() {
        if(RadioLocation.isChecked()){
            RadioLocation.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        }else {
            RadioLocation.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        }
    }

    private void setHomeState() {
        if(RadioHome.isChecked()){
            RadioHome.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        }else {
            RadioHome.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        }
    }
}
