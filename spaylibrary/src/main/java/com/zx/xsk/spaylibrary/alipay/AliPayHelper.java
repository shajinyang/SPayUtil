package com.zx.xsk.spaylibrary.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.zx.xsk.spaylibrary.alipay.bean.OrderBean;
import com.zx.xsk.spaylibrary.alipay.common.AuthResult;
import com.zx.xsk.spaylibrary.alipay.common.PayDemoActivity;
import com.zx.xsk.spaylibrary.alipay.common.PayResult;
import com.zx.xsk.spaylibrary.alipay.configs.ConfigData;
import com.zx.xsk.spaylibrary.alipay.util.DateUtil;
import com.zx.xsk.spaylibrary.alipay.util.OrderInfoUtil2_0;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付
 * Created by sjy on 2017/10/28.
 */

public class AliPayHelper {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Activity activity;
    private OrderBean orderBean;

    @SuppressLint("HandlerLeak")
    private  Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };


    private  AliPayHelper(String appid, String pid, String targetid, String rs2, String rs,OrderBean orderBean){
        ConfigData.setAPPID(appid);
        ConfigData.setPID(pid);
        ConfigData.setTargetId(targetid);
        ConfigData.setRsa2Private(rs2);
        ConfigData.setRsaPrivate(rs);
        this.orderBean=orderBean;
    }

    public  void pay(final Activity mContext){
        activity=mContext;
        if (TextUtils.isEmpty(ConfigData.APPID) || (TextUtils.isEmpty(ConfigData.RSA2_PRIVATE) && TextUtils.isEmpty(ConfigData.RSA_PRIVATE))) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            mContext.finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (ConfigData.RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(ConfigData.APPID, rsa2,orderBean);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? ConfigData.RSA2_PRIVATE : ConfigData.RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 构建器，构建支付必须的参数
     */
    public static class PBuilder{
       private String appid;
       private  String pid;
       private String targetid;
       private String rs2;
       private String rs;

       private OrderBean orderBean;

       public  PBuilder setAppId(String appid){
           this.appid=appid;
           return this;
        }

        public PBuilder setPid(String pid){
           this.pid=pid;
           return this;
       }

       public PBuilder setTargetId(String targetId){
            this.targetid=targetId;
            return this;
       }

       public PBuilder setRs2(String rs2){
           this.rs2=rs2;
           return this;
       }

       public PBuilder setRs(String rs){
           this.rs=rs;
           return this;
       }

       public PBuilder setTradeNo(String tradeNo){
           if(orderBean==null){
               orderBean=new OrderBean();
           }
           orderBean.setOut_trade_no(tradeNo);
           return this;
       }

       public  PBuilder setSubject(String subject){
           if(orderBean==null){
               orderBean=new OrderBean();
           }
           orderBean.setSubject(subject);
           return this;
       }

       public PBuilder setTotalMoney(String money){
           if(orderBean==null){
               orderBean=new OrderBean();
           }
           orderBean.setTotal_amount(money);
           return this;
       }

       public PBuilder setBody(String body){
           if(orderBean==null){
               orderBean=new OrderBean();
           }
           orderBean.setBody(body);
           return this;
       }

       public AliPayHelper create(){
           return new AliPayHelper(appid,pid,targetid,rs2,rs,orderBean);
       }
    }

}
