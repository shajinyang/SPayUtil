package com.zx.xsk.spayutil;

import com.zx.xsk.nethelper.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by sjy on 2017/10/30.
 */

public interface ApiService {

    //获取支付配置
    @FormUrlEncoded
    @POST("index/pay/get_ali_config")
    Observable<HttpResult<AliConfigBean>> alipayconfig(@FieldMap Map<String,Object> parms);

}
