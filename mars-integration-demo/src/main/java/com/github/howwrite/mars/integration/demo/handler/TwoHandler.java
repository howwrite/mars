package com.github.howwrite.mars.integration.demo.handler;

import com.github.howwrite.mars.integration.support.MarsMessageHandler;
import com.github.howwrite.mars.sdk.facade.MarsResponseFactory;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;
import com.github.howwrite.mars.sdk.request.MarsVoiceRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author howwrite
 * @date 2020/4/5 下午10:15:42
 */
@Order(2)
@Component
public class TwoHandler implements MarsMessageHandler {
    @Override
    public BaseMarsResponse doText(MarsTextRequest request) {
        return MarsResponseFactory.createTextResponse(request, "hello world");
    }

    @Override
    public BaseMarsResponse doVoice(MarsVoiceRequest request) {
        return MarsResponseFactory.createTextResponse(request, request.getRecognition());
    }
}
