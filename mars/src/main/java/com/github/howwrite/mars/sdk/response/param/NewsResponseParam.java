package com.github.howwrite.mars.sdk.response.param;

import com.github.howwrite.mars.sdk.utils.ParamUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 图文消息参数
 *
 * @author howwrite
 * @date 2020/3/15 下午10:03:10
 */
@Getter
@Setter
@ToString(callSuper = true)
public class NewsResponseParam implements Serializable {
    private static final long serialVersionUID = -4887654889668007647L;
    /**
     * 图文消息标题
     */
    private String title;
    /**
     * 图文消息描述
     */
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String picUrl;

    /**
     * 点击图文消息跳转链接
     */
    private String url;

    public NewsResponseParam(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String convertXmlString() {
        return String.format("<item> <Title><![CDATA[%s]]></Title>\n" +
                "<Description><![CDATA[%s]]></Description>\n" +
                "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>" +
                "</item>", getTitle(), getDescription(), getPicUrl(), getUrl());
    }

    public void checkParam() {
        ParamUtils.notBlank(getUrl(), "The url in the result cannot be empty");
        ParamUtils.notBlank(getTitle(), "The title in the result cannot be empty");
        ParamUtils.notBlank(getPicUrl(), "PicUrl in the result cannot be empty");
        ParamUtils.notBlank(getDescription(), "The description in the result cannot be empty");
    }
}
