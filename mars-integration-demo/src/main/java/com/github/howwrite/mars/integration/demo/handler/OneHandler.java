package com.github.howwrite.mars.integration.demo.handler;

import com.github.howwrite.mars.integration.support.MarsMessageHandler;
import com.github.howwrite.mars.sdk.facade.MarsResponseFactory;
import com.github.howwrite.mars.sdk.facade.MarsWxUtils;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author howwrite
 * @date 2020/4/5 下午10:15:42
 */
@Order(1)
@Component
@Slf4j
@RequiredArgsConstructor
public class OneHandler implements MarsMessageHandler {
    private final MarsWxUtils marsWxUtils;

    @Override
    public BaseMarsResponse doText(MarsTextRequest request) {
        return MarsResponseFactory.createTextResponse(request, "hello i am one");
    }
}