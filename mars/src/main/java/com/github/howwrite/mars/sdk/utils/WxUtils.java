package com.github.howwrite.mars.sdk.utils;

import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.exception.MarsErrorCode;
import com.github.howwrite.mars.sdk.exception.MarsWxException;
import com.github.howwrite.mars.sdk.utils.wx.aes.AesException;
import com.github.howwrite.mars.sdk.utils.wx.aes.Sha1;
import com.github.howwrite.mars.sdk.utils.wx.aes.WxBizMsgCrypt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author howwrite
 * @Description 微信消息工具类，涉及消息加解密
 * @create 2019/12/15 21:50
 */
@Component
public class WxUtils {
    private static final Logger log = LoggerFactory.getLogger(WxUtils.class);
    private final WxBizMsgCrypt wxBizMsgCrypt;
    private MarsWxProperties marsWxProperties;

    public WxUtils(MarsWxProperties webWxProperties) throws MarsWxException {
        this.marsWxProperties = webWxProperties;
        try {
            wxBizMsgCrypt = new WxBizMsgCrypt(webWxProperties.getToken(), webWxProperties.getEncodingAesKey(), webWxProperties.getAppId());
        } catch (AesException e) {
            log.warn("create WxMpUtil fail", e);
            throw new MarsWxException(MarsErrorCode.CREATE_WX_MSG_CRYPT_FAIL);
        }
    }

    /**
     * 验证消息是否合法
     *
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public Boolean checkSignature(String timestamp, String nonce, String signature) {
        try {
            return Sha1.gen(marsWxProperties.getToken(), timestamp, nonce)
                    .equals(signature);
        } catch (Exception e) {
            log.warn("Checking signature failed, and the reason is :", e);
            throw new MarsWxException(MarsErrorCode.CHECK_WX_SIGNATURE_FAIL);
        }
    }

    public Map<String, String> parseXml(InputStream inputStream, String signature, String timeStamp, String nonce) throws MarsWxException {
        String msg = streamToString(inputStream);
        return parseXml(msg, signature, timeStamp, nonce);
    }

    /**
     * @param input:微信传来的消息请求
     * @return 处理后的微信消息bean
     */
    public Map<String, String> parseXml(String input, String signature, String timeStamp, String nonce) throws MarsWxException {
        Map<String, String> map = new HashMap<String, String>(10);
        if (StringUtils.isEmpty(input)) {
            return new HashMap<>(16);
        }
        Document document;
        try {
            document = DocumentHelper.parseText(input);
        } catch (DocumentException e) {
            log.warn("parse xml fail", e);
            throw new MarsWxException(MarsErrorCode.PARSE_XML_FAIL);
        }
        Element rootElement = document.getRootElement();
        String encrypt = rootElement.elementText("Encrypt");
        if (encrypt != null) {
            try {
                input = wxBizMsgCrypt.decryptMsg(signature, timeStamp, nonce, encrypt);
                rootElement = DocumentHelper.parseText(input).getRootElement();
            } catch (Exception e) {
                log.warn("parse encrypt xml fail:", e);
                throw new MarsWxException(MarsErrorCode.PARSE_XML_FAIL);
            }
            map.put("encryption", "true");
            log.debug("Encryption mode");
        } else {
            map.put("encryption", "false");
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
     * @param inputStream
     * @return String类型结果
     * @throws IOException
     */
    private String streamToString(InputStream inputStream) {
        log.debug("current stream convert string");
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.warn("stream convert string fail", e);
        }

        log.debug("stream convert string result:[{}]", sb.toString());
        return sb.toString();
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
        try {
            return wxBizMsgCrypt.encryptMsg(replyMsg, timeStamp, nonce);
        } catch (AesException e) {
            log.warn("encrypt error", e);
            throw new MarsWxException(MarsErrorCode.ENCRYPT_FAIL, e);
        }
    }
}
