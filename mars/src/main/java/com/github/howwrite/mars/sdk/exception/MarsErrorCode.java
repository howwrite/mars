package com.github.howwrite.mars.sdk.exception;

/**
 * @author howwrite
 * @Description 错误码对应列表
 * @date 2019/12/15 21:11
 */
public interface MarsErrorCode {
    /**
     * 请配置:'mars.path'
     */
    String NOT_FOUND_PATH = "mars.not.found.path";
    /**
     * 创建微信消息加解密工具失败
     */
    String CREATE_WX_MSG_CRYPT_FAIL = "mars.create.wx.msg.crypt.fail";
    /**
     * 验证微信签名失败
     */
    String CHECK_WX_SIGNATURE_FAIL = "mars.check.wx.signature.fail";
    /**
     * 解析xml失败
     */
    String PARSE_XML_FAIL = "mars.parse.xml.fail";
    /**
     * 加密失败
     */
    String ENCRYPT_FAIL = "mars.encrypt.fail";

    /**
     * 无法处理此请求
     */
    String UNABLE_TO_PROCESS_THIS_REQUEST = "mars.unable.to.process.this.request";

    /**
     * 暂时未支持该类型的请求
     */
    String NOT_CURRENTLY_SUPPORTED_TYPE = "mars.not.currently.supported.type";

    /**
     * 未知的参数类型
     */
    String UNKNOWN_PARAMETER_TYPE = "unknown.parameter.type";
    /**
     * 响应中的 toUserName 不能为空
     */
    String RESPONSE_TO_USER_NAME_CAN_NOT_BE_EMPTY = "mars.response.to.user.name.can.not.be.empty";
    /**
     * 响应中的 fromUserName 不能为空
     */
    String RESPONSE_FROM_USER_NAME_CAN_NOT_BE_EMPTY = "mars.response.from.user.name.can.not.be.empty";
    /**
     * 结果中的 content 不能为空
     */
    String RESPONSE_CONTENT_CAN_NOT_BE_EMPTY = "mars.response.content.can.not.be.empty";
    /**
     * 结果中的 mediaId 不能为空
     */
    String RESPONSE_MEDIA_ID_CAN_NOT_BE_EMPTY = "mars.response.media.id.can.not.be.empty";
    /**
     * 结果中的 thumbMediaId 不能为空
     */
    String RESPONSE_THUMB_MEDIA_ID_CAN_NOT_BE_EMPTY = "mars.response.thumb.media.id.can.not.be.empty";
    /**
     * 结果中的 文章列表 不能为空
     */
    String RESPONSE_ARTICLE_LIST_CAN_NOT_BE_EMPTY = "mars.response.article.list.can.not.be.empty";
    /**
     * 结果中的 url 不能为空
     */
    String RESPONSE_URL_CAN_NOT_BE_EMPTY = "mars.response.url.can.not.be.empty";
    /**
     * 结果中的 title 不能为空
     */
    String RESPONSE_TITLE_CAN_NOT_BE_EMPTY = "mars.response.title.can.not.be.empty";
    /**
     * 结果中的 picUrl 不能为空
     */
    String RESPONSE_PIC_URL_CAN_NOT_BE_EMPTY = "mars.response.pic.url.can.not.be.empty";

    /**
     * 结果中的 description 不能为空
     */
    String RESPONSE_DESCRIPTION_CAN_NOT_BE_EMPTY = "mars.response.description.can.not.be.empty";
}
