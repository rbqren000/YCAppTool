package com.yc.common.encypt;

import android.util.Base64;
import android.view.View;

import com.yc.appencryptlib.Base64Utils;
import com.yc.appfilelib.AppFileUtils;
import com.yc.common.R;
import com.yc.library.base.mvp.BaseActivity;
import com.yc.roundcorner.view.RoundTextView;
import com.yc.appencryptlib.Md5EncryptUtils;
import com.yc.toolutils.AppLogUtils;

import java.io.File;


public class EncyptActivity extends BaseActivity implements View.OnClickListener {

    private RoundTextView tvView1;
    private RoundTextView tvView2;
    private RoundTextView tvView3;
    private RoundTextView tvView4;
    private RoundTextView tvView5;
    private RoundTextView tvView6;
    private RoundTextView tvView7;
    private RoundTextView tvView8;
    private RoundTextView tvView9;
    private RoundTextView tvView10;
    private RoundTextView tvView11;
    private RoundTextView tvView12;

    @Override
    public int getContentView() {
        return R.layout.activity_base_view;
    }

    @Override
    public void initView() {
        tvView1 = findViewById(R.id.tv_view_1);
        tvView2 = findViewById(R.id.tv_view_2);
        tvView3 = findViewById(R.id.tv_view_3);
        tvView4 = findViewById(R.id.tv_view_4);
        tvView5 = findViewById(R.id.tv_view_5);
        tvView6 = findViewById(R.id.tv_view_6);
        tvView7 = findViewById(R.id.tv_view_7);
        tvView8 = findViewById(R.id.tv_view_8);
        tvView9 = findViewById(R.id.tv_view_9);
        tvView10 = findViewById(R.id.tv_view_10);
        tvView11 = findViewById(R.id.tv_view_11);
        tvView12 = findViewById(R.id.tv_view_12);

    }

    @Override
    public void initListener() {
        tvView1.setOnClickListener(this);
        tvView2.setOnClickListener(this);
        tvView3.setOnClickListener(this);
        tvView4.setOnClickListener(this);
        tvView5.setOnClickListener(this);
        tvView6.setOnClickListener(this);
        tvView7.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tvView1.setText("1.MD5相关加密案例");
        tvView2.setText("2.base64相关加解密案例");
    }

    @Override
    public void onClick(View v) {
        if (v == tvView1) {
            md5();
        } else if (v == tvView2) {
            base64();
        } else if (v == tvView3) {
        } else if (v == tvView4) {
        } else if (v == tvView5) {
        } else if (v == tvView6){
        } else if (v == tvView7){

        }
    }

    private void md5() {
        String string = "yangchong";
        String md1 = Md5EncryptUtils.getMD5(string);
        AppLogUtils.d("md5计算字符串1: " + md1);
        String md2 = Md5EncryptUtils.encryptMD5ToString(string);
        AppLogUtils.d("md5计算字符串2: " + md2);
        String md3 = Md5EncryptUtils.encryptMD5ToString(string,"doubi");
        AppLogUtils.d("md5计算字符串，加盐处理3: " + md3);
        String md4 = Md5EncryptUtils.encryptMD5ToString(string.getBytes());
        AppLogUtils.d("md5计算字节数组4: " + md4);
        String md5 = Md5EncryptUtils.encryptMD5ToString(string.getBytes(),"doubi".getBytes());
        AppLogUtils.d("md5计算字节数组，加盐处理5: " + md5);

        String txt = AppFileUtils.getExternalFilePath(this, "txt");
        String md6 = Md5EncryptUtils.encryptMD5File1(txt);
        AppLogUtils.d("md5计算文件路径6: " + md6);
        String md7 = Md5EncryptUtils.encryptMD5File2(new File(txt));
        AppLogUtils.d("md5计算文件File7: " + md7);

        String fileName = txt + File.separator + "yc1.txt";
        String md8 = Md5EncryptUtils.encryptMD5File1(fileName);
        AppLogUtils.d("md5计算文件txt路径8: " + md8);
        String md9 = Md5EncryptUtils.encryptMD5File2(new File(fileName));
        AppLogUtils.d("md5计算文件txt路径9: " + md9);
    }

    private void base64() {
        // 加密传入的数据是byte类型的。并不是使用decode方法将原始数据转二进制。String类型的数据 使用 str.getBytes()就可以
        String str = "yangchong";
        // 在这里使用的是encode方式。返回的是byte类型加密数据，可使用new String转为String类型
        byte[] encode = Base64.encode(str.getBytes(), Base64.DEFAULT);
        String strBase64 = new String(encode);
        AppLogUtils.i("base64", "encode >>>" + strBase64);
        // 这里 encodeToString 则直接将返回String类型的加密数据
        String enToStr = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
        AppLogUtils.i("base64", "encodeToString >>> " + enToStr);
        // 对base64加密后的数据进行解密
        AppLogUtils.i("base64", "decode >>>" + new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT)));

        String strBase64_2 = Base64Utils.encodeToString(str);
        AppLogUtils.i("base64", "encode 2 >>>" + strBase64_2);
        String strBase64_3 = Base64Utils.decodeToString(strBase64_2);
        AppLogUtils.i("base64", "decode 3 >>>" + strBase64_3);
    }


}
