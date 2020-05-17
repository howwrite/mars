# 生成响应消息
mars将响应封装成一个工厂类，提供了各种类型消息的生成方法，[对应源码](../mars/src/main/java/com/github/howwrite/mars/sdk/facade/MarsResponseFactory.java)
[参考文档](https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Passive_user_reply_message.html)

## method
```java
public class MarsResponseFactory {
    /**
     * 生成图文类型响应
     *
     * @param request 请求
     * @param params  图文参数列表
     * @return 图文类型的响应
     */
    public static BaseMarsResponse createNewsResponse(BaseMarsRequest request, List<NewsResponseParam> params);

    /**
     * 生成图文类型响应
     *
     * @param request    请求
     * @param articles   图文参数列表
     * @param toUserName 要返回的用户openId
     * @return 图文类型的响应
     */
    public static BaseMarsResponse createNewsResponse(BaseMarsRequest request, List<NewsResponseParam> articles, String toUserName);

    /**
     * 生成音乐类型响应
     *
     * @param request      用户请求
     * @param title        音乐标题
     * @param description  音乐描述
     * @param musicUrl     音乐链接
     * @param hqMusicUrl   高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @return 音乐类型的响应
     */
    public static BaseMarsResponse createMusicResponse(BaseMarsRequest request, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId);


    /**
     * 生成音乐类型响应
     *
     * @param request      用户请求
     * @param title        音乐标题
     * @param description  音乐描述
     * @param musicUrl     音乐链接
     * @param hqMusicUrl   高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @param toUserName   要返回的用户openId
     * @return 音乐类型的响应
     */
    public static BaseMarsResponse createMusicResponse(BaseMarsRequest request, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId, String toUserName);

    /**
     * 创建视频类型的响应
     *
     * @param request     用户请求
     * @param mediaId     通过素材管理中的接口上传多媒体文件，得到的id
     * @param title       视频消息的标题
     * @param description 视频消息的描述
     * @return 视频类型的响应
     */
    public static BaseMarsResponse createVideoResponse(BaseMarsRequest request, String mediaId, String title, String description);

    /**
     * 创建视频类型的响应
     *
     * @param request     用户请求
     * @param mediaId     通过素材管理中的接口上传多媒体文件，得到的id
     * @param title       视频消息的标题
     * @param description 视频消息的描述
     * @param toUserName  要返回的用户openId
     * @return 视频类型的响应
     */
    public static BaseMarsResponse createVideoResponse(BaseMarsRequest request, String mediaId, String title, String description, String toUserName);

    /**
     * 创建语音消息的响应
     *
     * @param request 用户请求
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
     * @return 语音类型的响应
     */
    public static BaseMarsResponse createVoiceResponse(BaseMarsRequest request, String mediaId);

    /**
     * 创建语音消息的响应
     *
     * @param request    用户请求
     * @param mediaId    通过素材管理中的接口上传多媒体文件，得到的id
     * @param toUserName 要返回的用户openId
     * @return 语音类型的响应
     */
    public static BaseMarsResponse createVoiceResponse(BaseMarsRequest request, String mediaId, String toUserName);

    /**
     * 创建文本类型的响应
     *
     * @param request 用户请求
     * @param content 文本内容
     * @return 文本类型的响应
     */
    public static BaseMarsResponse createTextResponse(BaseMarsRequest request, String content);

    /**
     * 创建文本类型的响应
     *
     * @param request    用户请求
     * @param content    文本内容
     * @param toUserName 要返回的用户openId
     * @return 文本类型的响应
     */
    public static BaseMarsResponse createTextResponse(BaseMarsRequest request, String content, String toUserName);

    /**
     * 创建图片类型的响应
     *
     * @param request 用户请求
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id。
     * @return 图片类型的响应
     */
    public static BaseMarsResponse createImageResponse(BaseMarsRequest request, String mediaId);

    /**
     * 创建图片类型的响应
     *
     * @param request    用户请求
     * @param mediaId    通过素材管理中的接口上传多媒体文件，得到的id。
     * @param toUserName 要返回的用户openId
     * @return 图片类型的响应
     */
    public static BaseMarsResponse createImageResponse(BaseMarsRequest request, String mediaId, String toUserName);
}
```
+ demo参考[mars-integration-demo项目](../mars-integration-demo/src/main)