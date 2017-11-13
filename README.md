# 支付类库（一行代码搞定支付）
![](s.png)![](j.png)![](y.png)
### 基本介绍
####  现在接入支付功能的app越来越多，每次都去copy jar包或者copy代码 ，实在是麻烦，所以封装了一个支付帮助类，一行代码完成支付。


### 如何使用

#### Android Studio
    第一步：
      在项目的gradle里配置
      allprojects {
      		repositories {
      			...
      			maven { url 'https://jitpack.io' }
      		}
      	}

      第二步：
      在module的gradle里配置
      dependencies {
      	        compile 'com.github.shajinyang:SPayUtil:1.0.0'
      	}

### 使用示例

#### 支付宝支付
    new AliPayHelper
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
        .pay(MainActivity.this);

#### 微信支付
    new WxPayHelper
        .PBuilder()
        .setAppId("wxf8b4f85f3a794e77")
        .setNonceStr("测试")
        .setPartnerId("123243243")
        .setPrePayId("2324353454654")
        .setSign("424eerer3243dfsfer43")
        .create()
        .pay(MainActivity.this);








