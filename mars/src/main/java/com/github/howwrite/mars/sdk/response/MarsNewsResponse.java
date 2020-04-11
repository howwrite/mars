package com.github.howwrite.mars.sdk.response;

import com.github.howwrite.mars.sdk.constants.WxMsgType;
import com.github.howwrite.mars.sdk.exception.MarsErrorCode;
import com.github.howwrite.mars.sdk.exception.MarsIllegalParamException;
import com.github.howwrite.mars.sdk.response.param.NewsResponseParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 图文消息回复
 *
 * @author howwrite
 * @date 2020/3/10 下午12:21:29
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarsNewsResponse extends BaseMarsResponse {
    private static final long serialVersionUID = -3149093660315706214L;

    private List<NewsResponseParam> articles;

    @Override
    public String convertXmlString(String createTime) {
        String parentXml = super.convertXmlString(createTime);
        String format = "<xml>" +
                "%s" +
                "<ArticleCount>%s</ArticleCount>" +
                "<Articles>%s</Articles>" +
                "</xml>";
        StringBuilder result = new StringBuilder();
        for (NewsResponseParam article : articles) {
            result.append(article.convertXmlString());
        }
        return String.format(format, parentXml, articles.size(), result.toString());
    }

    @Override
    public void checkParam() {
        super.checkParam();
        if (CollectionUtils.isEmpty(getArticles())) {
            throw new MarsIllegalParamException(MarsErrorCode.RESPONSE_ARTICLE_LIST_CAN_NOT_BE_EMPTY);
        }
        articles.forEach(NewsResponseParam::checkParam);
    }

    @Override
    public String getMsgType() {
        return WxMsgType.NEWS_TYPE;
    }
}
