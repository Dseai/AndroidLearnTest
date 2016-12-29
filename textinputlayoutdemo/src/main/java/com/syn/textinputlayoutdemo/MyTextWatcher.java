package com.syn.textinputlayoutdemo;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 孙亚楠 on 2016/12/6.
 */

/**
 * 动态监听输入过程是否合法
 */
public class MyTextWatcher implements TextWatcher {
    private  View view;
    private  MainActivity context;
    public MyTextWatcher(View view,MainActivity context) {
        this.view=view;
        this.context=context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        switch (view.getId()){
            case R.id.account:
                context.isAccountValid();
                break;
            case R.id.password:
                context.isPasswordValid();
                break;
            case R.id.email:
                context.isEmailValid();
                break;
        }

    }
}
