package com.github.howwrite.mars.marsdemo.controller;

import com.github.howwrite.mars.sdk.facade.MarsResponseFactory;
import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;
import com.github.howwrite.mars.sdk.request.MarsVoiceRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author howwrite
 * @Description demo controller
 * @date 2019/12/15 15:31
 */
@Controller
public class MarsController {

    @PostMapping("/mars")
    public BaseMarsResponse helloMars(BaseMarsRequest request) {
        if (request instanceof MarsTextRequest) {
            return MarsResponseFactory.createTextResponse(request, "hello " + ((MarsTextRequest) request).getContent());
        }
        if (request instanceof MarsVoiceRequest) {
            MarsVoiceRequest voiceRequest = (MarsVoiceRequest) request;
            if (StringUtils.isBlank(((MarsVoiceRequest) request).getRecognition())) {
                return MarsResponseFactory.createVoiceResponse(request, voiceRequest.getMediaId());
            }
            return MarsResponseFactory.createTextResponse(request, "You are talking about " + ((MarsVoiceRequest) request).getRecognition());
        }
        return MarsResponseFactory.createTextResponse(request, "hello world");
    }
}
