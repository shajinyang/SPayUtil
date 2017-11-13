package com.zx.xsk.spayutil;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        getNet();
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
                       /* new AliPayHelper
                                .PBuilder()
                                .setAppId(stringHttpResult.data.getPARTNER())
                                .setPid(stringHttpResult.data.getSELLER())
                                .setRs2(stringHttpResult.data.getRSA_PRIVATE())
                                .setBody("")
                                .setSubject("测试")
                                .setTotalMoney("0.01")
                                .setTradeNo("637366373663736")
                                .setTargetId("737363")
                                .create()
                                .pay(MainActivity.this);*/

                        new WxPayHelper
                                .PBuilder()
                                .setAppId("wxf8b4f85f3a794e77")
                                .setNonceStr("测试")
                                .setPartnerId("123243243")
                                .setPrePayId("2324353454654")
                                .setSign("424eerer3243dfsfer43")
                                .create()
                                .pay(MainActivity.this);
                    }

                });
    }
}
