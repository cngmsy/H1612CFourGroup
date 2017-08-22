package com.jiyun.qcloud.dashixummoban.ui.mycenter;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.DataSynEvent;
import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.entity.HeadEventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 */
public class MyPageFragment extends BaseFragment implements MyContract.View{

    @BindView(R.id.tv_user_setting)
    ImageView tvUserSetting;
    @BindView(R.id.iv_user_notice)
    ImageView ivUserNotice;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.login)
    ImageView login;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.ll_userinfo)
    LinearLayout llUserinfo;
    @BindView(R.id.address)
    ImageView address;
    @BindView(R.id.main)
    LinearLayout main;
    Unbinder unbinder;
    private TextView change_tx;
    private TextView look_big;
    private TextView qux;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;
     MyContract.Presenter presenter;
    private String homeData;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_page;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void initView(View view) {
       EventBus.getDefault().register(this);//订阅
    }


    @Override
    public void setBundle(Bundle bundle) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(HeadEventBean event) {
        String img = event.getIconurl();
        String screen_name = event.getScreen_name();
        Glide.with(getActivity().getApplicationContext()).load(img).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageHead) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                ciDrawable.setCircular(true);
                imageHead.setImageDrawable(ciDrawable);
            }
        });
        username.setText(screen_name);
        username.setVisibility(View.VISIBLE);
       login.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(DataSynEvent event) {
        String image = event.getImage_Head();
        String name = event.getName();
        Glide.with(getActivity().getApplicationContext()).load(image).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageHead) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                ciDrawable.setCircular(true);
                imageHead.setImageDrawable(ciDrawable);
            }
        });
        username.setText(name);
        username.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除订阅
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                Log.e("相册上传", uri + "");
                crop(uri);
                Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageHead) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                        ciDrawable.setCircular(true);
                        imageHead.setImageDrawable(ciDrawable);
                    }
                });
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                // 得到图片的全路径
                Bitmap bitmap = data.getParcelableExtra("data");
                final Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                Log.e("拍照上传--REQUEST_CUT", uri + "");
                Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageHead) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                        ciDrawable.setCircular(true);
                        //imageHead.setImageDrawable(ciDrawable);
                    }
                });

                HashMap<String, String> map = new HashMap<>();
                final File file = saveBitmapFile(bitmap);
                map.put("file", file + "");

               /* Gson gson = new Gson();
                final HeadResult headResult = gson.fromJson(homeData, HeadResult.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), headResult.getResult(), Toast.LENGTH_SHORT).show();
                    }
                });
*/
                /*//头像上传操作
                HashMap<String, String> map = new HashMap<>();
                final File file = saveBitmapFile(bitmap);
                map.put("file", file + "");
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS);


                OkHttpClient mOkHttpClient = builder.build();

                MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                mbody.addFormDataPart("image", file.getName(), RequestBody.create(null, file));
                RequestBody requestBody = mbody.build();
                Request request = new Request.Builder()
                        .url("http://123.206.14.104:8080/FileUploadDemo/FileUploadServlet")
                        .post(requestBody)
                        .build();
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        Head shangchuang = gson.fromJson(string, Head.class);
                        final HeadResult shangchuang2 = gson.fromJson(shangchuang.getData(), HeadResult.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), shangchuang2.getResult(), Toast.LENGTH_SHORT).show();
                                String localurl = shangchuang2.getUrl().replace("localhost", "123.206.14.104");
                                Log.e("localurl", localurl);

                                sharedPreferences = getActivity().getSharedPreferences("zc", Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                editor.putString("picture", localurl);
                                editor.commit();
                                Glide.with(getActivity().getApplicationContext()).load(localurl).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageHead) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                                        ciDrawable.setCircular(true);
                                        imageHead.setImageDrawable(ciDrawable);
                                    }
                                });
                            }
                        });
                    }
                });*/
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //拿到bitmap，做喜欢做的事情把  ---> 显示 or上传？
            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
            Log.e("拍照上传--REQUEST_CAREMA", uri + "");
            //myfragment_touxiang.setImageBitmap(bitmap);
            Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageHead) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                    ciDrawable.setCircular(true);
                    imageHead.setImageDrawable(ciDrawable);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_head, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_head:
                mypop();
                break;
            case R.id.login:
                Intent intent = new Intent(getContext(), MobActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void mypop() {
        final PopupWindow popupWindow = new PopupWindow();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mypop, null);
        change_tx = (TextView) view.findViewById(R.id.change_tx);
        look_big = (TextView) view.findViewById(R.id.look_big);
        qux = (TextView) view.findViewById(R.id.qux);

        popupWindow.setContentView(view);
        popupWindow.setHeight(400);
        popupWindow.setWidth(600);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(getActivity().findViewById(R.id.main), Gravity.CENTER | Gravity.CENTER, 0, 0);
        change_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });
        look_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, PHOTO_REQUEST_CAREMA);
            }
        });
        qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public File saveBitmapFile(Bitmap bitmap) {
        File file = new File("/sdcard/head.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(MyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showImageHead(Head head) {
        homeData = head.getData();
    }
}
