package com.github.howwrite.mars.sdk.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关工具类
 *
 * @author howwrite
 * @date 2020/4/30 上午8:33:50
 */
public class MarsStringUtils {
    private final static Pattern FILENAME_PATTERN = Pattern.compile("filename=\"(.*)\"");

    /**
     * 获取图片名称
     *
     * @param contentDisposition 微信header中的contentDisposition参数，例如attachment; filename="MEDIA_ID.jpg"
     * @return 通过参数解析出的文件名，例如media_id.jpg
     */
    public static String getFileName(String contentDisposition) {
        if (!StringUtils.isEmpty(contentDisposition)) {
            Matcher matcher = FILENAME_PATTERN.matcher(contentDisposition);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }
}
