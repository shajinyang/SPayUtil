package com.zx.xsk.spayutil;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加统一请求头拦截器
 * Created by sjy on 2017/10/26.
 */

public class HeadInterceptor {
    static String val;
    static String key="shhasd123asd123s";
    public static Interceptor get(){
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String json="{\"time\":\""+ System.currentTimeMillis()+"\"}";
                Logger.e(System.currentTimeMillis()+"");
                try {
                    val=AesUtils.encryptString(json,key,0).trim();
                    Logger.e(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Request request= chain.request();
                Request.Builder builder=request.newBuilder();
                builder.addHeader("sign",val);
                return chain.proceed(builder.build());
            }
        };
        return interceptor;
    }
}
