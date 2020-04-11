package com.github.howwrite.mars.test.test;

import com.github.howwrite.mars.test.BaseMarsTest;
import okhttp3.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * 模拟微信请求测试controller消息加解密
 *
 * @author howwrite
 * @date 2020/3/7 上午9:42:09
 */
public class TestController extends BaseMarsTest {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    private static final String REQUEST_PATH = "/testMars";
    private static final MediaType XML_MEDIA_TYPE
            = MediaType.get("application/xml; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    @LocalServerPort
    private int port;

    /**
     * 测试get请求验证签名
     */
    @Test
    public void testCheckSign() throws Exception {
        String echostr = "abcdefg";
        String parameters = "signature=d9ab76da6454d9a719af76994a2424a1b82162bd&nonce=384783965&timestamp=1583549822&echostr=" + echostr;
        Request request = new Request.Builder()
                .url(getUrl() + "?" + parameters)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Assert.assertEquals(200, response.code());
            String result = response.body().string();
            log.debug("request result:[{}]", result);
            Assert.assertEquals(echostr, result.trim());
        }
    }

    /**
     * 测试文本格式明文消息
     */
    @Test
    public void testTextLaws() throws Exception {
        String body = "<xml><ToUserName><![CDATA[testToUserName]]></ToUserName><FromUserName><![CDATA[testFromUserName]]></FromUserName><CreateTime>123456</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[hello白羊座]]></Content><MsgId>1</MsgId></xml>";
        try (Response response = client.newCall(createTextLawsRequest(body, "")).execute()) {
            Assert.assertEquals(200, response.code());
            String result = response.body().string();
            log.debug("request result:[{}]", result);
            Document document = DocumentHelper.parseText(result);
            Element rootElement = document.getRootElement();
            String content = rootElement.elementText("Content");
            String toUserName = rootElement.elementText("ToUserName");
            String fromUserName = rootElement.elementText("FromUserName");
            String msgType = rootElement.elementText("MsgType");
            // 发送内容是helloMars, 断言结果是: 用户发送内容+ too
            Assert.assertEquals("hello白羊座 too", content);
            Assert.assertEquals("testToUserName", fromUserName);
            Assert.assertEquals("testFromUserName", toUserName);
            Assert.assertEquals("text", msgType);
        }
    }

    /**
     * 测试文本格式兼容消息
     */
    @Test
    public void testTextCompatible() throws Exception {
        String body = "<xml><ToUserName><![CDATA[testToUserName]]></ToUserName>    <FromUserName><![CDATA[testFromUserName]]></FromUserName>    <CreateTime>123456</CreateTime>    <MsgType><![CDATA[text]]></MsgType>    <Content><![CDATA[helloMars]]></Content>    <MsgId>1</MsgId>    <Encrypt><![CDATA[Lmq5gxVEDHIdoEy3NUH86DVXkk2R0z4vCx9cL190d9Kntww43Bpjr3a4lXakn4G4ERpvAXuYYnulg97I5zuZ2bpeUr6au+znWt6tR5eedHZWnUyWLmZc0XX2Y/sK0uEHUtaFsYC9kwXqF/7HTQ1mJ8nT1OTyVtXCckYqHbL4xS0vvUDUqmEdyQEuhU56hjufqo6gzmKZCvz13BCjVqHFfWNV/2jGEtodwhl4fg6/9wxSjnqZXcUKX200jNioKGP43jTzZff7iZiVMuyaCApp7hA/XWRUBlFsrez2uVgSwQIEwddG2joCKxPEG4UEKKcP/8UJ9d9+rq41FdhMCh7pSa7prN5NkDhzIcsjqFC1Uj7IFi/P6OIUVxG64e7LWgDFf70evMsBtHEy78AZ/NK89zNMNeTXjmtxupM0MnwVUHtjCmnD5CZCkw9HlvqC7qdzGis2nGFgxta7CposUGGqSw==]]></Encrypt></xml>";
        String parameters = "signature=f41c13f383640b9cb10c22cf2d0bc43994efd3c8&timestamp=1583549370&nonce=423099419";
        try (Response response = client.newCall(createTextLawsRequest(body, parameters)).execute()) {
            Assert.assertEquals(200, response.code());
            String result = response.body().string();
            Assert.assertNotNull(result);
        }
    }


    /**
     * 测试文本格式加密消息
     */
    @Test
    public void testTextEncryption() throws Exception {
        String body = "<xml>    <ToUserName><![CDATA[testToUserName]]></ToUserName>    <Encrypt><![CDATA[XmHu1XUAjxg5xqiwM8tjy2Stmm4q8lG8o44o7+0kjChEDQh98/VGu2z4qYkXICradNRp4bFk8gkaREqN9Tg3bVeRB4ulg5wh/0ywHs3ZoL7JlLYagEHeB/cnJvY/QSuRU2x1Fg4DZlQ8kLUTaR4eV6LHAgvWv2uZa+q8lODkeEx5+oHdt9Br8CziQ99L23vrHr5c8Q4tAWebS6slhh7J/hsFzTLn3RwB0H3v8EXHRm0WyAGlgxCML/D5MdLOydlTIc3TPk1HBG8p0qcxtE501nIzyyB+XPv5mkY4zXjsJ65u7JrSsvEhXprOsYFNluDOOrpO5J2tP/dlf8vCfxXdLJiekl5dqPxF2NFFMX/ORdud6OMXfAsAV9KnNI4R8sbERgC+zEBMx5uw0KcwVvOwBF36/lWn1gWUFbhAmUxH+UYwJPHYigVRiQdOmW+nGGqqfYYAJUPrgyulRqRdgxa7Ug==]]></Encrypt></xml>";
        String parameters = "signature=069da1d26873136a1f7cd67e9f5dd3e868f9b67f&timestamp=1583549661&nonce=2072430883";
        try (Response response = client.newCall(createTextLawsRequest(body, parameters)).execute()) {
            Assert.assertEquals(200, response.code());
            String result = response.body().string();
            Assert.assertNotNull(result);
        }
    }

    private String getUrl() {
        return "http://localhost:" + port + REQUEST_PATH;
    }


    /**
     * 生成请求
     */
    public Request createTextLawsRequest(String body, String parameters) {
        RequestBody requestBody = RequestBody.create(XML_MEDIA_TYPE, body);
        return new Request.Builder()
                .url(getUrl() + "?" + parameters)
                .post(requestBody)
                .build();
    }
}
