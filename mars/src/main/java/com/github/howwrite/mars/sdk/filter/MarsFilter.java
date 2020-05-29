package com.github.howwrite.mars.sdk.filter;

import com.github.howwrite.mars.sdk.constants.MarsConstants;
import com.github.howwrite.mars.sdk.exception.MarsException;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author howwrite
 * @Description 过滤微信相关请求，解析参数中加密的字段值
 * @date 2019/12/15 15:25
 */
@Slf4j
@RequiredArgsConstructor
public class MarsFilter implements Filter {
    public static final int ORDER = 0;

    private final WxUtils wxUtils;

    /**
     * 对于 get 请求，计算出校验码直接返回
     *
     * @param request  请求
     * @param response 响应
     * @param chain    过滤器链
     * @throws IOException      io异常
     * @throws ServletException 服务异常
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().equals(MarsConstants.MARS_WX_CHECK_REQUEST_METHOD)) {
            checkSignature(req, resp);
            return;
        }
        chain.doFilter(req, resp);
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
            throw new MarsException("Failed to verify WeChat signature");
        }
    }

}
