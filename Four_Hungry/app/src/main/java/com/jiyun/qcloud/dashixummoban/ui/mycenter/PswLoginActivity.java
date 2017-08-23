package com.jiyun.qcloud.dashixummoban.ui.mycenter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.DataSynEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class PswLoginActivity extends BaseActivity {

    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btlogin)
    Button btlogin;
    private String phone;
    private String psw;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_psw_login;
    }


    @OnClick({R.id.iv_login_back, R.id.btlogin})
    public void onViewClicked(View view) {
        phone = etPhone.getText().toString().trim();
        psw = etPsw.getText().toString().trim();
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.btlogin:
                if (phone.equals("") && psw.equals("")) {
                    Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    if ("123".equals (phone)&& "123".equals(psw)) {
                        EventBus.getDefault().post(new DataSynEvent("豆豆","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=394981062,2590200520&fm=26&gp=0.jpg"));
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
