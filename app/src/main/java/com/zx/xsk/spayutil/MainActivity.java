package com.zx.xsk.spayutil;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zx.xsk.nethelper.BaseSubscriber;
import com.zx.xsk.nethelper.HttpResult;
import com.zx.xsk.nethelper.NetHelper;
import com.zx.xsk.spaylibrary.alipay.AliPayHelper;
import com.zx.xsk.spaylibrary.wechart.WxPayHelper;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ali_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNet();
            }
        });
        findViewById(R.id.wx_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWxNet();
            }
        });
    }

    private void getNet(){
        NetHelper.getInstance()
                .create(ApiService.class)
                .alipayconfig(new HashMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<AliConfigBean>>() {
                    @Override
                    public void onStartNet() {

                    }

                    @Override
                    public void onErrorNet(Throwable throwable) {

                    }

                    @Override
                    public void onCompletedNet() {

                    }

                    @Override
                    public void onNextNet(HttpResult<AliConfigBean> stringHttpResult) {
                        new AliPayHelper().pay(MainActivity.this,"");

                    }

                });
    }
    private void getWxNet(){
        NetHelper.getInstance()
                .create(ApiService.class)
                .wxConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<WxResBean>() {
                    @Override
                    public void onStartNet() {

                    }

                    @Override
                    public void onErrorNet(Throwable throwable) {

                    }

                    @Override
                    public void onCompletedNet() {

                    }

                    @Override
                    public void onNextNet(WxResBean wxResBean) {
                        new WxPayHelper
                                .PBuilder()
                                .setAppId(wxResBean.getAppid())
                                .setNonceStr("测试")
                                .setPartnerId(wxResBean.getPartnerid())
                                .setPrePayId(wxResBean.getPrepayid())
                                .setSign(wxResBean.getSign())
                                .create()
                                .pay(MainActivity.this);
                    }

                });
    }


}
