package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.exception.MarsErrorCode;
import com.github.howwrite.mars.sdk.exception.MarsIllegalParamException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * @author howwrite
 * @date 2020/3/3 上午8:20:29
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsImageResponse extends BaseMarsResponse {
    private static final long serialVersionUID = 3780289963468833618L;
    private String mediaId;

    @Override
    public String getMsgType() {
        return WxMsgType.IMAGE_TYPE;
    }

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>%s<Image><MediaId><![CDATA[%s]]></MediaId></Image></xml>";
        return String.format(format, parentXml, getMediaId());
    }

    @Override
    public void checkParam() {
        super.checkParam();
        if (StringUtils.isEmpty(getMediaId())) {
            throw new MarsIllegalParamException(MarsErrorCode.RESPONSE_MEDIA_ID_CAN_NOT_BE_EMPTY);
        }
    }
}
