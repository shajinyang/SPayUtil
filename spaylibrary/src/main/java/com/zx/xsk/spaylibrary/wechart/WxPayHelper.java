package com.zx.xsk.spaylibrary.wechart;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zx.xsk.spaylibrary.wechart.beans.WxOrderBean;

/**
 * 微信支付
 * Created by sjy on 2017/11/13.
 */

public class WxPayHelper {
    private WxOrderBean wxOrderBean;
    private Context mContext;
    private IWXAPI api;


    private WxPayHelper(WxOrderBean wxOrderBean ){
        this.wxOrderBean=wxOrderBean;
    }

    public void pay(Context mContext){
        this.mContext=mContext;
        api = WXAPIFactory.createWXAPI(mContext,wxOrderBean.getAppId());
        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId			= wxOrderBean.getAppId();
        req.partnerId		= wxOrderBean.getPartnerId();
        req.prepayId		= wxOrderBean.getPrepayId();
        req.nonceStr		= wxOrderBean.getNonceStr();
        req.timeStamp		= System.currentTimeMillis()+"";
        req.packageValue	= "Sign=WXPay";
        req.sign			= wxOrderBean.getSign();
        req.extData			= "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    public static class PBuilder{
        private WxOrderBean wxOrderBean;

        public PBuilder setAppId(String appId){
            if(wxOrderBean==null){
                wxOrderBean=new WxOrderBean();
            }
            wxOrderBean.setAppId(appId);
            return this;
        }


        public PBuilder setPartnerId(String partnerId){
            if(wxOrderBean==null){
                wxOrderBean=new WxOrderBean();
            }
            wxOrderBean.setPartnerId(partnerId);
            return this;
        }


        public PBuilder setPrePayId(String prePayId){
            if(wxOrderBean==null){
                wxOrderBean=new WxOrderBean();
            }
            wxOrderBean.setPrepayId(prePayId);
            return this;
        }

        public PBuilder setNonceStr(String nonceStr){
            if(wxOrderBean==null){
                wxOrderBean=new WxOrderBean();
            }
            wxOrderBean.setNonceStr(nonceStr);
            return this;
        }

        public PBuilder setSign(String sign){
            if(wxOrderBean==null){
                wxOrderBean=new WxOrderBean();
            }
            wxOrderBean.setSign(sign);
            return this;
        }
        public WxPayHelper create(){
            return new WxPayHelper(wxOrderBean);
        }
    }
}
