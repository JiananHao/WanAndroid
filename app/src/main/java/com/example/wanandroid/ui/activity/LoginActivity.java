package com.example.wanandroid.ui.activity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseAcitivty;
import com.example.wanandroid.contract.LoginContract;
import com.example.wanandroid.model.bean.UserBean;
import com.example.wanandroid.presenter.LoginPresenter;

public class LoginActivity extends BaseAcitivty<LoginPresenter> implements LoginContract.View {

    private EditText userName,passWord;
    private Button loginButton;
    private String name,pass;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        super.initInject();
        activityComponent.inject(this);
    }

    @Override
    protected void initView() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        userName = findViewById(R.id.et_ensure_username);
        passWord = findViewById(R.id.et_ensure_password);
        loginButton = findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOK()){
                    Log.d("hao","=======login=====GO==");
                    mPresenter.login(name,pass);
                }
            }
        });
    }

    private boolean isOK(){
        name = userName.getText().toString().trim();
        pass = passWord.getText().toString().trim();
        if (name.length() < 6 || pass.length() <6){
            Toast.makeText(this,"账号或密码必须大于6位！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    public void loginOk(UserBean userBean) {
        Toast.makeText(this,"登录成功！",Toast.LENGTH_SHORT).show();
        finish();
    }
}
