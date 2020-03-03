package com.github.howwrite.mars.marsdemo.controller;

import com.github.howwrite.mars.sdk.request.MarsWxRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsWxResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author howwrite
 * @Description demo controller
 * @create 2019/12/15 15:31
 */
@RestController
public class MarsController {

    @PostMapping("/mars")
    public BaseMarsWxResponse helloMars(MarsWxRequest request) {

        return BaseMarsWxResponse.createTextResponse(request, "hello " + request.getContent());
    }
}
