package com.github.howwrite.mars.sdk.info;

import com.github.howwrite.mars.sdk.constants.MarsConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 微信服务器ip详情，包括callBack地址以及api接口地址
 * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_the_WeChat_server_IP_address.html
 *
 * @author howwrite
 * @date 2020/5/29 上午10:36:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatServerIpListInfo extends BaseMarsInfo {
    private static final long serialVersionUID = -8823460103043417007L;
    private List<String> ipList;

    public WeChatServerIpListInfo(List<String> ipList) {
        this.ipList = ipList;
    }

    public WeChatServerIpListInfo() {
    }


    public static WeChatServerIpListInfo fail(String... errorMsgs) {
        WeChatServerIpListInfo weChatServerIpListInfo = new WeChatServerIpListInfo();
        fullFailParams(weChatServerIpListInfo, "please see " + MarsConstants.GET_THE_WECHAT_SERVER_IP_ADDRESS_DEVELOP_URL, errorMsgs);
        return weChatServerIpListInfo;
    }

    public static WeChatServerIpListInfo fail(Throwable throwable, String... errorMsgs) {
        WeChatServerIpListInfo weChatServerIpListInfo = new WeChatServerIpListInfo();
        fullFailParams(weChatServerIpListInfo, throwable, "please see " + MarsConstants.GET_THE_WECHAT_SERVER_IP_ADDRESS_DEVELOP_URL, errorMsgs);
        return weChatServerIpListInfo;
    }
}
