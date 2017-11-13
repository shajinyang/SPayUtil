![](sjylogo.png)
# 支付类库（一行代码搞定支付）

### 基本介绍
####  基于支付宝和微信支付做了封装。极大的提高了接入支付的开发速度。一行代码接入支付，一行代码调起支付。非常方便。


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








