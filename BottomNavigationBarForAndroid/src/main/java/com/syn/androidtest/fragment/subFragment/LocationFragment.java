package com.syn.androidtest.fragment.subFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syn.androidtest.R;

/**
 * Created by 孙亚楠 on 2016/12/14.
 */
public class LocationFragment extends Fragment {
    public static LocationFragment newInstance(String s) {
        LocationFragment locationFragment=new LocationFragment();
        Bundle bundle=new Bundle();
        bundle.putString("args",s);
        locationFragment.setArguments(bundle);
        return locationFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_sub_content,container,false);
        Bundle bundle=getArguments();
        String s=bundle.getString("args");
        TextView textView=(TextView)view.findViewById(R.id.fragment_text_view);
        textView.setText(s);
        return view;

    }
}


