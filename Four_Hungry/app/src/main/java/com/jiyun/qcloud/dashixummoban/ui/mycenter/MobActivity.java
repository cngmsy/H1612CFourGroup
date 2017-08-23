package com.jiyun.qcloud.dashixummoban.ui.mycenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.HeadEventBean;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MobActivity extends BaseActivity {

    @BindView(R.id.iv_user_back)
    ImageView ivUserBack;
    @BindView(R.id.iv_user_password_login)
    TextView ivUserPasswordLogin;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.tv_user_code)
    TextView tvUserCode;
    @BindView(R.id.et_user_code)
    EditText etUserCode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.logo_qq)
    ImageView logoQq;
    @BindView(R.id.logo_sinaweibo)
    ImageView logoSinaweibo;
    @BindView(R.id.logo_weixin_friend)
    ImageView logoWeixinFriend;
    private EventHandler eventHandler;
    private int recLen = 60;
    private Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (recLen >= 0) {
                recLen--;
                tvUserCode.setText("获取验证码" + recLen);
                handler.postDelayed(this, 1000);
            } else {
                tvUserCode.setText("获取验证码");
            }
        }
    };

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SMSSDK.setAskPermisionOnReadContact(true);

        //理解为一个事件的handler
        eventHandler = new EventHandler() {
            @Override//注册的时候走这个方法
            public void onRegister() {
                super.onRegister();
            }

            @Override//事件提交完成的时候走这个方法
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                if (data instanceof Throwable) {//失败
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MobActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {//SMSSDK.RESULT_COMPLETE
                    //SMSSDK.EVENT_GET_VERIFICATION_CODE
                    if (event == SMSSDK.RESULT_COMPLETE) {
                        // 处理你自己的逻辑
                        //弹一个吐司表示注册成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MobActivity.this, "验证码验证成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            Toast.makeText(MobActivity.this, "验证码获取成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override//事件提交之前走这个方法
            public void beforeEvent(int i, Object o) {
                super.beforeEvent(i, o);
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mob;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_user_code, R.id.iv_user_back, R.id.iv_user_password_login, R.id.bt_login, R.id.logo_qq, R.id.logo_sinaweibo, R.id.logo_weixin_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_back:
                finish();
                break;
            case R.id.iv_user_password_login:
                Intent intent = new Intent(MobActivity.this,PswLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_user_code:
                handler.postDelayed(runnable, 1000);
                SMSSDK.getVerificationCode("86", etUserPhone.getText().toString().trim());  //获取验证码
                break;
            case R.id.bt_login:
                String code = etUserCode.getText().toString().trim();
                String phoneNum = etUserPhone.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    Toast.makeText(getApplicationContext(), "手机号码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(getApplicationContext(), "验证码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.submitVerificationCode("86", etUserPhone.getText().toString().trim(), etUserCode.getText().toString().trim());//提交验证码
                break;
            case R.id.logo_qq:
                Login(SHARE_MEDIA.QQ);
                break;
            case R.id.logo_sinaweibo:
                Login(SHARE_MEDIA.SINA);
                break;
            case R.id.logo_weixin_friend:
                Login(SHARE_MEDIA.WEIXIN);
                Toast.makeText(this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    private void Login(SHARE_MEDIA share_media) {
        UMShareAPI mShareAPI = UMShareAPI.get(MobActivity.this);
        mShareAPI.getPlatformInfo(MobActivity.this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                String temp = "";
                for (String key : data.keySet()) {
                    temp = temp + key + " : " + data.get(key) + "\n";
                }

                Toast.makeText(MobActivity.this, platform + " 成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                EventBus.getDefault().post(new HeadEventBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=394981062,2590200520&fm=26&gp=0.jpg","豆豆"));
                finish();
                Toast.makeText(MobActivity.this, platform + " 成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(MobActivity.this, platform + " 取消了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在onResume方法里面注册handler
        SMSSDK.registerEventHandler(eventHandler);
        //在onResume方法里面获取国家代码
        SMSSDK.getSupportedCountries();
    }


    @Override
    protected void onPause() {
        super.onPause();
        //在方法里面解注册handler
        SMSSDK.unregisterEventHandler(eventHandler);
    }

}

