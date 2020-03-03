package com.github.howwrite.mars.sdk.filter;

import com.github.howwrite.mars.sdk.constants.MarsConstants;
import com.github.howwrite.mars.sdk.exception.MarsErrorCode;
import com.github.howwrite.mars.sdk.exception.MarsWxException;
import com.github.howwrite.mars.sdk.extension.MarsParameterServletRequest;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author howwrite
 * @Description 过滤微信相关请求，解析参数中加密的字段值
 * @create 2019/12/15 15:25
 */
public class MarsFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(MarsFilter.class);

    private final WxUtils wxUtils;

    public MarsFilter(WxUtils wxUtils) {
        this.wxUtils = wxUtils;
    }

    /**
     * 对于 get 请求，计算出返回的
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().equals(MarsConstants.MARS_WX_CHECK_REQUEST_METHOD)) {
            checkSignature(req, resp);
            return;
        }
        if (req.getMethod().equals(MarsConstants.MARS_WX_HANDLE_MESSAGE_METHOD)) {
            HttpServletRequest marsRequest = parsingTheMessage(req, resp);
            chain.doFilter(marsRequest, resp);
            return;
        }
        throw new MarsWxException(MarsErrorCode.UNABLE_TO_PROCESS_THIS_REQUEST);
    }

    private HttpServletRequest parsingTheMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter(MarsConstants.MARS_WX_SIGNATURE_PARAM_NAME);
        String echostr = request.getParameter(MarsConstants.MARS_WX_ECHOSTR_PARAM_NAME);
        String timestamp = request.getParameter(MarsConstants.MARS_WX_TIMESTAMP_PARAM_NAME);
        String nonce = request.getParameter(MarsConstants.MARS_WX_NONCE_PARAM_NAME);
        log.debug("decrypt message.signature:{}, echostr:{}, timestamp:{}, nonce:{}", signature, echostr, timestamp, nonce);

        ServletInputStream inputStream = request.getInputStream();
        Map<String, String> map = wxUtils.parseXml(inputStream, signature, timestamp, nonce);

        MarsParameterServletRequest marsParameterServletRequest = new MarsParameterServletRequest(request);
        map.forEach((key, value) -> {
            marsParameterServletRequest.putParameter(StringUtils.uncapitalize(key), value);
        });
        return marsParameterServletRequest;
    }


    /**
     * 验证签名
     *
     * @param request  请求内容
     * @param response response
     */
    private void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter(MarsConstants.MARS_WX_SIGNATURE_PARAM_NAME);
        String echostr = request.getParameter(MarsConstants.MARS_WX_ECHOSTR_PARAM_NAME);
        String timestamp = request.getParameter(MarsConstants.MARS_WX_TIMESTAMP_PARAM_NAME);
        String nonce = request.getParameter(MarsConstants.MARS_WX_NONCE_PARAM_NAME);
        log.debug("check signature.signature:{}, echostr:{}, timestamp:{}, nonce:{}", signature, echostr, timestamp, nonce);

        if (wxUtils.checkSignature(timestamp, nonce, signature)) {
            log.debug("verification success.echostr:{}", echostr);
            response.getWriter().println(echostr);
        } else {
            throw new MarsWxException(MarsErrorCode.CHECK_WX_SIGNATURE_FAIL);
        }
    }

}
