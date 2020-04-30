配置说明
yml配置文件案例

```yaml
mars:
  path: /mars
  weixin:
    app-id: ${WEIXIN_APP_ID:}
    encoding-aes-key: ${WEIXIN_AES_KEY:}
    token: ${WEIXIN_TOKEN:}
    app-secret: ${WEIXIN_SECRET:}
```

配置项|含义|数据类型|是否必填
:-|:-|:-|:-
mars.path|接收微信消息的controller地址|String|是
mars.weixin.app-id|微信公众号后台配置的appId|String|是
mars.weixin.token|微信公众号后台配置的token|String|是
mars.weixin.encoding-aes-key|微信公众号后台配置的encodingAesKey|String|是
mars.weixin.app-secret|微信公众号后台的appSecret|String|是
mars.weixin.get-access-token-retry|获取AccessToken失败重试次数，默认3次|Integer|否