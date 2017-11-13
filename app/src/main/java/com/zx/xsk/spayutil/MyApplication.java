package com.zx.xsk.spayutil;

import android.app.Application;

import com.zx.xsk.nethelper.NetHelper;

/**
 * Created by sjy on 2017/10/30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetHelper.init(this,"http://api.85zx.cn/index.php/",HeadInterceptor.get());
    }
}
