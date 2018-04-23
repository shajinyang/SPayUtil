package com.zx.xsk.spayutil;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sjy on 2017/11/14.
 */

public class WxResBean {

    /**
     * appid : wxb4ba3c02aa476ea1
     * partnerid : 1900006771
     * package : Sign=WXPay
     * noncestr : a0323aeed5ca5b181dbd33553207dcba
     * timestamp : 1510624500
     * prepayid : wx20171114095500c7479f41b20862337685
     * sign : 78EFC7F2067794C2EB1DDDEE248F18BC
     */

    private String appid;
    private String partnerid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String prepayid;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
