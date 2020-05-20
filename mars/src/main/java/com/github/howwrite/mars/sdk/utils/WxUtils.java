package com.github.howwrite.mars.sdk.utils;

import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.exception.MarsEncryptException;
import com.github.howwrite.mars.sdk.exception.MarsException;
import com.github.howwrite.mars.sdk.utils.wx.aes.Sha1;
import com.github.howwrite.mars.sdk.utils.wx.aes.WxBizMsgCrypt;
import com.google.common.io.CharStreams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author howwrite
 * @Description 微信消息工具类，涉及消息加解密
 * @date 2019/12/15 21:50
 */
@Component
public class WxUtils {
    private static final
    Logger log = LoggerFactory.getLogger(WxUtils.class);
    private final WxBizMsgCrypt wxBizMsgCrypt;
    private final MarsWxProperties marsWxProperties;

    public WxUtils(MarsWxProperties marsWxProperties) throws MarsException {
        this.marsWxProperties = marsWxProperties;
        ParamUtils.notBlank(marsWxProperties.getToken(), "Please configure the token provided by WeChat");
        ParamUtils.notBlank(marsWxProperties.getAppId(), "Please configure the app_id provided by WeChat");
        ParamUtils.notBlank(marsWxProperties.getEncodingAesKey(), "Please configure encoding Aes Key provided by WeChat");
        wxBizMsgCrypt = new WxBizMsgCrypt(marsWxProperties.getToken(), marsWxProperties.getEncodingAesKey(), marsWxProperties.getAppId());
    }

    /**
     * 验证消息是否合法
     *
     * @param timestamp 时间戳
     * @param nonce     nonce
     * @param signature 签名
     * @return 签名是否合法
     */
    public Boolean checkSignature(String timestamp, String nonce, String signature) {
        try {
            return Sha1.gen(marsWxProperties.getToken(), timestamp, nonce)
                    .equals(signature);
        } catch (Exception e) {
            throw new MarsEncryptException("Failed to verify WeChat signature", e);
        }
    }

    public Map<String, String> parseXml(InputStream inputStream, String signature, String timeStamp, String nonce) throws MarsException {
        String msg = streamToString(inputStream);
        return parseXml(msg, signature, timeStamp, nonce);
    }

    /**
     * @param input:微信传来的消息请求
     * @return 处理后的微信消息bean
     */
    public Map<String, String> parseXml(String input, String signature, String timeStamp, String nonce) throws MarsException {
        Map<String, String> map = new HashMap<>(16);
        if (StringUtils.isEmpty(input)) {
            return new HashMap<>(16);
        }
        Document document;
        try {
            document = DocumentHelper.parseText(input);
        } catch (DocumentException e) {
            throw new MarsEncryptException("Failed to parse xml", e);
        }
        Element rootElement = document.getRootElement();
        String encrypt = rootElement.elementText("Encrypt");
        if (!StringUtils.isEmpty(encrypt)) {
            try {
                input = wxBizMsgCrypt.decryptMsg(signature, timeStamp, nonce, encrypt);
                rootElement = DocumentHelper.parseText(input).getRootElement();
            } catch (Exception e) {
                throw new MarsEncryptException("xml parsing failed", e);
            }
            map.put("Encryption", "true");
            log.debug("Encryption mode");
        } else {
            map.put("Encryption", "false");
        }
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            map.put(element.getName(), element.getData().toString());
        }
        return map;
    }

    /**
     * inputStream转换为字符串的方法
     *
     * @param inputStream 流内容
     * @return String类型结果
     */
    private String streamToString(InputStream inputStream) {
        InputStreamReader in = new InputStreamReader(inputStream);
        try {
            return CharStreams.toString(in);
        } catch (IOException e) {
            throw new MarsEncryptException("Stream conversion string exception", e);
        }
    }


    /**
     * {@link #encryptMsg(String, String, String)}
     */
    public String encryptMsg(String replyMsg, String timeStamp) {
        return encryptMsg(replyMsg, timeStamp, null);
    }

    /**
     * 将公众平台回复用户的消息加密打包.
     * <ol>
     * 	<li>对要发送的消息进行AES-CBC加密</li>
     * 	<li>生成安全签名</li>
     * 	<li>将消息密文和安全签名打包成xml格式</li>
     * </ol>
     *
     * @param replyMsg  公众平台待回复用户的消息，xml格式的字符串
     * @param timeStamp 时间戳，可以自己生成，也可以用URL参数的timestamp
     * @param nonce     随机串，可以自己生成，也可以用URL参数的nonce
     * @return 加密后的可以直接回复用户的密文，包括msg_signature, timestamp, nonce, encrypt的xml格式的字符串
     */
    public String encryptMsg(String replyMsg, String timeStamp, String nonce) {
        return wxBizMsgCrypt.encryptMsg(replyMsg, timeStamp, nonce);
    }
}
