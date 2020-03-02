package com.github.howwrite.mars.marsdemo.controller;

import com.github.howwrite.mars.sdk.request.WxRequest;
import com.github.howwrite.mars.sdk.response.WxResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author howwrite
 * @Description:
 * @create 2019/12/15 15:31
 */
@RestController
public class MarsController {

    @PostMapping("/mars")
    public WxResponse helloMars(WxRequest request) {

        return WxResponse.createTextResponse(request, "hello " + request.getContent());
    }

    @GetMapping("/dd")
    public WxResponse hh() {
        WxResponse wxResponse = new WxResponse();
        wxResponse.setContent("dddccc");
        return wxResponse;
    }
}
