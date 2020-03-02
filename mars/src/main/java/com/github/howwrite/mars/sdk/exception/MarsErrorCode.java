package com.github.howwrite.mars.sdk.exception;

/**
 * @author howwrite
 * @Description:
 * @create 2019/12/15 21:11
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
    String UNABLE_TO_PROCESS_THIS_REQUEST = "unable.to.process.this.request";
}
