package com.github.howwrite.mars.integration.demo.support;

import com.github.howwrite.mars.integration.support.MarsCurtain;
import com.github.howwrite.mars.sdk.facade.MarsResponseFactory;
import com.github.howwrite.mars.sdk.request.BaseMarsRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author howwrite
 * @date 2020/4/5 下午10:40:36
 */
@Component
@Slf4j
public class MarsCurtainDemo implements MarsCurtain {
    @Override
    public BaseMarsResponse error(BaseMarsRequest request, Throwable throwable, Map<Object, Object> params) {
        log.warn("error", throwable);
        return MarsResponseFactory.createTextResponse(request, throwable.getMessage());
    }
}
