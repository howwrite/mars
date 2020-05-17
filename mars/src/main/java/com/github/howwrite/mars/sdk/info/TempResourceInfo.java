package com.github.howwrite.mars.sdk.info;

import com.github.howwrite.mars.sdk.constants.MarsConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 临时资源详情
 *
 * @author howwrite
 * @date 2020/4/29 上午10:19:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TempResourceInfo extends BaseMarsInfo {
    private static final long serialVersionUID = 4452984782644920457L;
    private String fileName;
    /**
     * 字节流
     */
    private byte[] bytes;

    public TempResourceInfo(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.bytes = bytes;
    }

    public TempResourceInfo() {
    }

    public static TempResourceInfo fail(String... errorMsgs) {
        TempResourceInfo tempResourceInfo = new TempResourceInfo();
        fullFailParams(tempResourceInfo, "please see " + MarsConstants.GET_TEMP_RESOURCES_DEVELOP_URL, errorMsgs);
        return tempResourceInfo;
    }

    public static TempResourceInfo fail(Throwable throwable, String... errorMsgs) {
        TempResourceInfo tempResourceInfo = new TempResourceInfo();
        fullFailParams(tempResourceInfo, throwable, "please see " + MarsConstants.GET_TEMP_RESOURCES_DEVELOP_URL, errorMsgs);
        return tempResourceInfo;
    }
}
