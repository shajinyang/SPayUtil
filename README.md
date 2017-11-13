# 支付类库
![](s.png)![](j.png)![](y.png)
### 基本介绍
####  一行代码搞定Android端支付宝，微信支付



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
      	        compile 'com.github.shajinyang:SjyNetHelper:1.1.0'
      	}

### 使用示例

#### 新建api接口（同retrofit）
    public interface ApiService {

         @FormUrlEncoded
         @POST("index/login/login")
         Observable<HttpResult> login(@FieldMap Map<String,Object> parms);

     }
    这里的HttpResult为网络response接收,一般根据自己的业务需求自行定义泛型。
    public class HttpResult<T> {
       public String error;
       public String info;
       public T data;
    }

#### 发起请求
    public void getNet(){

            HashMap<String,Object> map=new HashMap<>();
            map.put("params","2");
            map.put("params2","1");
            map.put("params3","1");

            NetHelper.getInstance()
                    .isCache(true)//可不设置默认不缓存
                    .cacheExpire(60*1000)//可不设置默认缓有效期1分钟
                    .create(ApiService.class)
                    .login(map)
                    .observeOn(AndroidSchedulers.mainThread())//线程控制
                    .subscribeOn(Schedulers.io())
                    .subscribe(new BaseSubscriber<HttpResult<T>>() {
                        @Override
                        public void onStartNet() {
                            //todo 请求开始
                        }

                        @Override
                        public void onErrorNet(Throwable e) {
                            //todo 请求错误
                        }

                        @Override
                        void onCompeteReq() {
                            //todo 请求结束
                        }

                        @Override
                        void onNextReq(HttpResult<T> h) {
                            //todo 处理数据
                        }

                    });

        }

        这里BaseSubscriber可以自己定义，如果你需要对返回参数统一处理，可以
        自定义YourSubscriber 继承BaseSubscriber,然后在YourSubscriber中处理
        你自己的逻辑。
        示例：
        public abstract class MySubscriber<T> extends BaseSubscriber<HttpResult<T>> {
            abstract void onSuccess(T t);
            abstract void onErrorNet();
            abstract void onFinish();

            @Override
            public void onStartNet() {

            }

            @Override
            public void onErrorNet(Throwable throwable) {
                Toaster.showToast(throwable.getMessage());
                onErrorNet();
            }

            @Override
            public void onCompletedNet() {
                onFinish();
            }

            @Override
            public void onNextNet(HttpResult<T> t) {
                if("0".equals(t.error)){
                    if(t.error!=null) {
                        onSuccess(t.data);
                    }
                }else {
                    Toaster.showToast(t.info+"");
                }

            }
        }

#### 其他说明

#### 添加公共头
    （1）可以在初始化时添加公共头部参数
    NetHelper.initStaticHeader(HashMap<String,String> map);
    （2）通过retrofit  在apiservice注解里添加
    （3）自己新增拦截器

#### 自定义拦截器
    NetHelper.init(this,yourapi,interceptor);
    可自定义添加单个或多个拦截器


    有时候特殊的业务需求，可能会有多个baseurl ，可以在apiservice 的url注解添加全路径









