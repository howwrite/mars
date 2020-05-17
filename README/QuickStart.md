# 引入依赖
```xml
<dependency>
    <groupId>com.github.howwrite</groupId>
    <artifactId>mars</artifactId>
    <version>(current pom version)</version>
</dependency>
```
+ version 使用此项目[最新RELEASE版本号](../pom.xml).

# 配置
配置一个spring boot风格的application.yml文件，把他放到资源目录中
```yaml
mars:
  path: /weixin
  weixin:
    app-id: ${WEIXIN_APP_ID:}
    encoding-aes-key: ${WEIXIN_AES_KEY:}
    token: ${WEIXIN_TOKEN:}
    app-secret: ${WEIXIN_SECRET:}
```
[配置说明](../README/config.md)

# 编写Controller
```java
@Controller
public class MarsController {
    @PostMapping("/mars")
    public BaseMarsResponse helloMars(BaseMarsRequest request) {
        if (request instanceof MarsTextRequest) {
            return MarsResponseFactory.createTextResponse(request, "hello " + ((MarsTextRequest) request).getContent());
        }
        return MarsResponseFactory.createTextResponse(request, "hello world");
    }
}
```
至此一个收到文本消息能回复 'hello+『用户发送的消息』' ，收到别的消息回复'hello world'的公众号已经实现

[参考 mars-demo 模块](../mars-demo/src/main/java/com/github/howwrite/mars/marsdemo/controller/MarsController.java)