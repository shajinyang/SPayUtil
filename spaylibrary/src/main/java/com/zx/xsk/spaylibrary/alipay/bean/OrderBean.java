package com.zx.xsk.spaylibrary.alipay.bean;

/**
 * 订单
 * Created by sjy on 2017/11/11.
 */

public class OrderBean {

    private String timeout_express="30m";
    private String product_code="QUICK_MSECURITY_PAY";
    private String total_amount;
    private String subject;
    private String body;
    private String out_trade_no;

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
