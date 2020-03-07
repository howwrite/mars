package com.github.howwrite.mars.marsdemo.controller;

import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author howwrite
 * @Description demo controller
 * @create 2019/12/15 15:31
 */
@Controller
public class MarsController {

    @PostMapping("/mars")
    public BaseMarsResponse helloMars(BaseMarsRequest request) {
        if (request instanceof MarsTextRequest) {
            return BaseMarsResponse.createTextResponse(request, "hello " + ((MarsTextRequest) request).getContent());
        }
        return BaseMarsResponse.createTextResponse(request, "hello world");
    }
}
