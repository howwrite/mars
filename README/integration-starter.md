# 高级功能快速开始
mars提供了更加方便的API，帮助开发者快速实现微信公众号回复功能

## 创建springBoot-web项目并引入依赖
```xml
<dependency>
    <groupId>com.github.howwrite</groupId>
    <artifactId>mars-integration</artifactId>
    <version>${mars.version}</version>
</dependency>
```
+ 此依赖会将mars模块传递依赖过来，所以可以移除mars模块的依赖
+ version 使用此项目[最新RELEASE版本号](../pom.xml).

## 配置公众号信息
```yaml
mars:
  path: /weixin
  weixin:
    app-id: ${WEIXIN_APP_ID:}
    encoding-aes-key: ${WEIXIN_AES_KEY:}
    token: ${WEIXIN_TOKEN:}
    app-secret: ${WEIXIN_SECRET:}
```

## 创建spring bean并注入
实现MarsMessageHandler接口，并注入spring容器
```java
@Component
public class OneHandler implements MarsMessageHandler {
    @Override
    public BaseMarsResponse doText(MarsTextRequest request) {
        return MarsResponseFactory.createTextResponse(request, "i am one");
    }

    @Override
    public BaseMarsResponse doVoice(MarsVoiceRequest request) {
        return MarsResponseFactory.createTextResponse(request, "你好");
    }
}
```
启动springboot的启动类。 至此，一个接收文字消息时回复『i am one』，接收语音消息回复『你好』的公众号已完成

demo参考[mars-integration-demo项目](../mars-integration-demo/src/main)