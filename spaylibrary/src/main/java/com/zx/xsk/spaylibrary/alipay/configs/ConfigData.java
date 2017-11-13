package com.zx.xsk.spaylibrary.alipay.configs;

/**
 * 基础配置信息
 * Created by sjy on 2017/10/28.
 */

public class ConfigData {
    /** 支付宝支付业务：入参app_id */
    public static  String APPID = "";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static  String PID = "";

    /** 支付宝账户登录授权业务：入参target_id值 */
    public static  String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static  String RSA2_PRIVATE = "";
    public static  String RSA_PRIVATE = "";

    public static String getAPPID() {
        return APPID;
    }

    public static void setAPPID(String APPID) {
        ConfigData.APPID = APPID;
    }

    public static String getPID() {
        return PID;
    }

    public static void setPID(String PID) {
        ConfigData.PID = PID;
    }

    public static String getTargetId() {
        return TARGET_ID;
    }

    public static void setTargetId(String targetId) {
        TARGET_ID = targetId;
    }

    public static String getRsa2Private() {
        return RSA2_PRIVATE;
    }

    public static void setRsa2Private(String rsa2Private) {
        RSA2_PRIVATE = rsa2Private;
    }

    public static String getRsaPrivate() {
        return RSA_PRIVATE;
    }

    public static void setRsaPrivate(String rsaPrivate) {
        RSA_PRIVATE = rsaPrivate;
    }
}
