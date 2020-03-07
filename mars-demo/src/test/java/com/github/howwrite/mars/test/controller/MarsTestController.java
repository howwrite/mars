package com.github.howwrite.mars.test.controller;

import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author howwrite
 * @date 2020/3/7 上午9:34:07
 */
@Controller
public class MarsTestController {

    @PostMapping("/testMars")
    public BaseMarsResponse handlerWxMessage(BaseMarsRequest request) {
        if (request instanceof MarsTextRequest) {
            return BaseMarsResponse.createTextResponse(request, ((MarsTextRequest) request).getContent() + " too");
        }
        return null;
    }

}
