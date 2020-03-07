package com.github.howwrite.mars.test.test;

import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import com.github.howwrite.mars.sdk.utils.wx.aes.Sha1;
import com.github.howwrite.mars.test.BaseMarsTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author howwrite
 * @date 2020/3/7 上午10:40:45
 */
public class TestWxUtil extends BaseMarsTest {

    @Resource
    private WxUtils wxUtils;

    @Resource
    private MarsWxProperties marsWxProperties;

    /**
     * 测试解密消息
     */
    @Test
    public void testDecrypt() {
        Map<String, Object> decryptResultMap = wxUtils.parseXml("<xml>    <ToUserName><![CDATA[testToUserName]]></ToUserName>    <Encrypt><![CDATA[XmHu1XUAjxg5xqiwM8tjy2Stmm4q8lG8o44o7+0kjChEDQh98/VGu2z4qYkXICradNRp4bFk8gkaREqN9Tg3bVeRB4ulg5wh/0ywHs3ZoL7JlLYagEHeB/cnJvY/QSuRU2x1Fg4DZlQ8kLUTaR4eV6LHAgvWv2uZa+q8lODkeEx5+oHdt9Br8CziQ99L23vrHr5c8Q4tAWebS6slhh7J/hsFzTLn3RwB0H3v8EXHRm0WyAGlgxCML/D5MdLOydlTIc3TPk1HBG8p0qcxtE501nIzyyB+XPv5mkY4zXjsJ65u7JrSsvEhXprOsYFNluDOOrpO5J2tP/dlf8vCfxXdLJiekl5dqPxF2NFFMX/ORdud6OMXfAsAV9KnNI4R8sbERgC+zEBMx5uw0KcwVvOwBF36/lWn1gWUFbhAmUxH+UYwJPHYigVRiQdOmW+nGGqqfYYAJUPrgyulRqRdgxa7Ug==]]></Encrypt></xml>"
                , "069da1d26873136a1f7cd67e9f5dd3e868f9b67f", "1583549661", "2072430883"
        );
        Assert.assertEquals("helloMars", decryptResultMap.get("Content"));
        Assert.assertEquals("123456", decryptResultMap.get("CreateTime"));
        Assert.assertEquals(true, decryptResultMap.get("Encryption"));
        Assert.assertEquals("testToUserName", decryptResultMap.get("ToUserName"));
        Assert.assertEquals("testFromUserName", decryptResultMap.get("FromUserName"));
        Assert.assertEquals("text", decryptResultMap.get("MsgType"));
        Assert.assertEquals("1", decryptResultMap.get("MsgId"));
    }

    /**
     * 测试加密消息
     */
    @Test
    public void testEncryption() throws Exception {
        String xmlString = "<xml><name>mars</name><Author>howwrite</Author></xml>";
        String timeStamp = "1234567";
        String nonce = "nonce";
        String encryption = wxUtils.encryptMsg(xmlString, timeStamp, nonce);
        String decryptSignature = Sha1.getSha1(marsWxProperties.getToken(), timeStamp, nonce);

        // 解密加密的消息
        Map<String, Object> decryptResult = wxUtils.parseXml(encryption, decryptSignature, timeStamp, nonce);
        Assert.assertEquals("mars", decryptResult.get("name"));
        Assert.assertEquals("howwrite", decryptResult.get("Author"));
    }
}
