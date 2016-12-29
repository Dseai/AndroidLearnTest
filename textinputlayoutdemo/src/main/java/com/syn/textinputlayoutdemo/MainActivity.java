package com.syn.textinputlayoutdemo;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public android.support.design.widget.TextInputLayout account,password,email;
    public EditText edt_account;
    public EditText edt_password;
    public EditText edt_email;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        account=(TextInputLayout)findViewById(R.id.account);
        password=(TextInputLayout)findViewById(R.id.password);
        email=(TextInputLayout)findViewById(R.id.email);
        login=(Button)findViewById(R.id.login);

        edt_account= (EditText) findViewById(R.id.edt_account);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_password=(EditText)findViewById(R.id.edt_password);

        //添加监听
        edt_account.addTextChangedListener(new MyTextWatcher(edt_account,this));
        edt_email.addTextChangedListener(new MyTextWatcher(edt_email, this));
        edt_password.addTextChangedListener(new MyTextWatcher(edt_password, this));

        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.login:
             CanLogin();
             break;
         default:
             break;
     }
    }

    private void CanLogin() {
        if(!isAccountValid()){
            Toast.makeText(MainActivity.this,"请检查账户名格式",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isPasswordValid()){
            Toast.makeText(MainActivity.this,"检查密码格式",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isEmailValid()){
            Toast.makeText(MainActivity.this,"请检查邮箱格式",Toast.LENGTH_SHORT).show();
        return;
        }

    }
    //检查账户名格式
    public boolean isAccountValid() {
        if(edt_account.getText().toString().trim().equals("")||edt_account.getText().toString().trim().isEmpty())
        {
            //设置错误提醒
            account.setError("不能为空");
            account.requestFocus();
            return false;
        }
        account.setErrorEnabled(false);
        return true;

    }
    //检查密码格式
    public boolean isPasswordValid() {
        if(edt_password.getText().toString().trim().equals("")||edt_password.getText().toString().trim().isEmpty()){
            password.setErrorEnabled(true);

            password.setError("不能为空");
            password.requestFocus();
            return  false;
        }
        password.setErrorEnabled(false);
        return true;
    }
    //检查email格式注意 android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches())的使用  验证邮箱格式
    public boolean isEmailValid() {
        String email1=edt_email.getText().toString().trim();
        if(TextUtils.isEmpty(email1)|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setErrorEnabled(true);
            email.setError("邮箱格式出错");
            email.requestFocus();
            return false;
        }
        email.setErrorEnabled(false);
        return  true;
    }




}
