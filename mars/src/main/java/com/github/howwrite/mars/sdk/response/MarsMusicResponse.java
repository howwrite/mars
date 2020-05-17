package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.utils.ParamUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * @author howwrite
 * @date 2020/3/8 上午8:43:51
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsMusicResponse extends BaseMarsResponse {
    private static final long serialVersionUID = -7434226028895186911L;
    /**
     * 音乐标题
     */
    private String title;
    /**
     * 音乐描述
     */
    private String description;
    /**
     * 音乐链接
     */
    private String musicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String hqMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String thumbMediaId;

    @Override
    public String getMsgType() {
        return WxMsgType.MUSIC_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        StringBuilder result = new StringBuilder("<xml>");
        result.append(super.convertXmlString(createTime));
        result.append("<Music><ThumbMediaId><![CDATA[").append(getThumbMediaId()).append("]]></ThumbMediaId>");
        if (!StringUtils.isEmpty(getTitle())) {
            result.append("<Title><![CDATA[").append(getTitle()).append("]]></Title>");
        }
        if (!StringUtils.isEmpty(getMusicUrl())) {
            result.append("<MusicUrl><![CDATA[").append(getMusicUrl()).append("]]></MusicUrl>");
        }
        if (!StringUtils.isEmpty(getHqMusicUrl())) {
            result.append("<HQMusicUrl><![CDATA[").append(getHqMusicUrl()).append("]]></HQMusicUrl>");
        }
        if (!StringUtils.isEmpty(getDescription())) {
            result.append("<Description><![CDATA[").append(getDescription()).append("]]></Description>");
        }
        result.append("</Music></xml>");
        return result.toString();
    }

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtils.notBlank(getThumbMediaId(), "The thumbMediaId in the result cannot be empty");
    }
}
