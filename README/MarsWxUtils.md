# 微信消息工具类
[工具类源码](../mars/src/main/java/com/github/howwrite/mars/sdk/facade/MarsWxUtils.java)

## 功能介绍
### AccessToken
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
public interface AccessTokenCacheExtend {
    /**
     * 保存 AccessToken
     *
     * @param accessToken 要保存AccessToken
     * @param expires     token有效时间，单位: 秒
     */
    void saveAccessToken(String accessToken, Integer expires);
    /**
     * 获取AccessToken
     *
     * @return AccessToken值
     */
    AccessTokenInfo getAccessToken();
}
```
当项目引入spring-boot-starter-data-redis依赖后会默认注入一个redis版本的默认实现类，当然，项目侧也可以实现此接口注入到spring容器中，此时默认实现类不会生效。
此接口可以同时存在多个实现类。当有多个实现类时`getAccessToken` 时会循环get，直至某一个实现类get的值不为null返回，都为空则调用 `refreshAccessToken`。save时会循环save。

### getTempResource(String mediaId)-获取临时资源
该方法传入一个临时资源id，返回资源的文件名以及字节流。使用此方法结果时需要调用`getSuccess`判定是否请求成功