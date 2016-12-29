package com.syn.notificationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by 孙亚楠 on 2016/12/28.
 */
public class OtherActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        TextView textView=(TextView)findViewById(R.id.text);
    }
}
