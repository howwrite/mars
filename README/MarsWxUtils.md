# 微信消息工具类(MarsWxUtils)
[工具类源码](../mars/src/main/java/com/github/howwrite/mars/sdk/facade/MarsWxUtils.java)

## 功能介绍
### 1. 获取AccessToken
+ getAccessToken()
该方法会先从缓存中获取AccessToken，如果缓存中没有则调用refreshAccessToken获取。
使用此方法时需要判断返回值的 `getSuccess` 来判定是否成功获取AccessToken

+ refreshAccessToken()
该方法会从微信服务器获取AccessToken，文档地址: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
获取成功后会保存到缓存中。

+ AccessToken缓存
mars将缓存AccessToken逻辑抽象为一个接口
`com.github.howwrite.mars.sdk.facade.MarsCacheExtend`
```java
public interface MarsCacheExtend {
    /**
     * 缓存值
     *
     * @param key     要保存的key
     * @param value   要保存的值
     * @param expires token有效时间，单位: 秒
     */
    void saveValue(String key, Object value, Integer expires);

    /**
     * 获取缓存值
     *
     * @param key 缓存的key
     * @return 缓存的值
     */
    @NotNull
    CacheInfo getValue(String key);
}
```
当项目引入spring-boot-starter-data-redis依赖并写入相关redis配置后会默认注入一个redis版本的默认实现类，当然，项目也可以实现此接口注入到spring容器中，此时自带的redis默认实现类不会生效。
此接口可以同时存在多个实现类。当有多个实现类时`getAccessToken` 时会循环get，直至某一个实现类get的值不为empty返回，都为空则调用 `refreshAccessToken`。save时会循环save。

### getTempResource(String mediaId)-获取临时资源
该方法传入一个临时资源id，返回资源的文件名以及字节流。使用此方法结果时需要调用`getSuccess`判定是否请求成功，失败的话会给出错误信息或者异常堆栈(不是所有失败都有异常堆栈)
```java
//...
import javax.annotation.Resource;
public class controller{

    @Resource
    private MarsWxUtils marsWxUtils;
    
    public void xxx(String mediaId){
        TempResourceInfo tempMediaImage = marsWxUtils.getTempResource(mediaId);
        if (tempMediaImage.getSuccess()) {
        // do success
        String fileName = tempMediaImage.getFileName();
        byte[] bytes = tempMediaImage.getBytes();
        } else {
            log.warn("get temp res error", tempMediaImage.getThrowable());
            return MarsResponseFactory.createTextResponse(request, tempMediaImage.getErrorMsg());
        }
    }
}
```