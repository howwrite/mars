# 高级功能
mars提供了更加方便的API，帮助开发者快速实现微信公众号回复功能，本文档将对高级功能进行详解。

## API
相关实现细节参考`com.github.howwrite.mars.integration.controller.MarsController`
### MarsMessageHandler
mars-integration 提供了各种类型的处理器。具体参考 com.github.howwrite.mars.integration.support.MarsMessageHandler 接口以及相关注释。

MarsMessageHandler的子类在一个spring容器中可以有多个，按照 @Order 规范进行排序，处理请求时，会循环调用不同MessageHandler实现类的对应方法，当返回值不为null时则表示成功获取返回值，返回给用户。

当请求经过所有handler后结果仍然是null，那么调用 `MarsCurtain#defaultHandler` 方法返回默认回复内容
### Curtain
mars-integration 同时提供了用于在请求前后以及发生异常时调用的API，对应 `com.github.howwrite.mars.integration.support.MarsCurtain`

*注: 此接口子类在一个spring容器中只可存在一个。*