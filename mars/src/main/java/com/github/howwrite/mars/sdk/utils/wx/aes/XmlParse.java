/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.github.howwrite.mars.sdk.utils.wx.aes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XMLParse class
 *
 * @author howwrite
 * @date 2020年03月02日 09:14:52
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
public class XmlParse {
    private static final Logger log = LoggerFactory.getLogger(XmlParse.class);

    /**
     * 生成xml消息
     *
     * @param encrypt   加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 生成的xml字符串
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {

        String format = "<xml>" + "<Encrypt><![CDATA[%1$s]]></Encrypt>"
                + "<MsgSignature><![CDATA[%2$s]]></MsgSignature>"
                + "<TimeStamp>%3$s</TimeStamp>" + "<Nonce><![CDATA[%4$s]]></Nonce>" + "</xml>";
        return String.format(format, encrypt, signature, timestamp, nonce);
    }
}
