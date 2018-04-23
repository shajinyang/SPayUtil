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


    public void pay(final Activity mContext,String orderInfo){
        activity=mContext;
        orderInfo ="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2088511789134513&biz_content=%7B%22body%22%3A%22%CE%D2%CA%C7%B2%E2%CA%D4%CA%FD%BE%DD%22%2C%22out_trade_no%22%3A%2212323455657%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%D6%A7%B8%B6%B2%E2%CA%D4Java%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=GBK&format=json&method=alipay.trade.app.pay&notify_url=%C9%CC%BB%A7%CD%E2%CD%F8%BF%C9%D2%D4%B7%C3%CE%CA%B5%C4%D2%EC%B2%BD%B5%D8%D6%B7&sign=PXLr%2BhIx0S%2FRuD1I7sHTKCyaMdL68odaRGoqP84A3CZoXdzOkzOik%2FmmFuSIWoSJSodxPapp8Z2YS0S2yGiBuAs79L9qpGBWMbyBLLPy8pnWNECyV4%2BiSaxCo46gi8abwKLeXimgq5AYtijwHlSRmhNtWhLZhXcsC75WxBAz5cQ%3D&sign_type=RSA2&timestamp=2018-03-12+15%3A44%3A31&version=1.0";
        final String finalOrderInfo = orderInfo;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(finalOrderInfo, true);
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


}
