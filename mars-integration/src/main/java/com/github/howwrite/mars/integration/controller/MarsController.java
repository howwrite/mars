package com.github.howwrite.mars.integration.controller;

import com.github.howwrite.mars.integration.support.MarsCurtain;
import com.github.howwrite.mars.integration.support.MarsMessageHandler;
import com.github.howwrite.mars.sdk.request.*;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author howwrite
 * @date 2020/3/22 下午1:04:22
 */
@Controller
@ConditionalOnBean(MarsMessageHandler.class)
public class MarsController {
    private List<MarsMessageHandler> messageHandlers;

    @Resource
    private MarsCurtain marsCurtain;

    @Autowired(required = false)
    public void setMessageHandlers(List<MarsMessageHandler> messageHandlers) {
        if (!CollectionUtils.isEmpty(messageHandlers)) {
            this.messageHandlers = messageHandlers;
            OrderComparator.sort(messageHandlers);
        }
    }

    @PostMapping(value = "${mars.path:/mars}")
    public BaseMarsResponse handle(BaseMarsRequest request) {
        BaseMarsResponse response = null;
        Map<Object, Object> params = new HashMap<>(16);
        try {
            marsCurtain.before(request, params);
            if (!CollectionUtils.isEmpty(messageHandlers)) {
                for (MarsMessageHandler messageHandler : messageHandlers) {
                    if (request instanceof MarsTextRequest) {
                        response = messageHandler.doText((MarsTextRequest) request);
                    } else if (request instanceof MarsEventRequest) {
                        response = messageHandler.doEvent((MarsEventRequest) request);
                    } else if (request instanceof MarsImageRequest) {
                        response = messageHandler.doImage((MarsImageRequest) request);
                    } else if (request instanceof MarsLinkRequest) {
                        response = messageHandler.doLink((MarsLinkRequest) request);
                    } else if (request instanceof MarsLocationRequest) {
                        response = messageHandler.doLocation((MarsLocationRequest) request);
                    } else if (request instanceof MarsShortVideoRequest) {
                        response = messageHandler.doShortVideo((MarsShortVideoRequest) request);
                    } else if (request instanceof MarsVideoRequest) {
                        response = messageHandler.doVideo((MarsVideoRequest) request);
                    } else if (request instanceof MarsVoiceRequest) {
                        response = messageHandler.doVoice((MarsVoiceRequest) request);
                    }
                    if (response != null) {
                        break;
                    }
                }
            }
            response = generateNonEmptyResults(request, response);
            return response;
        } catch (Throwable throwable) {
            response = generateNonEmptyResults(request, marsCurtain.error(request, throwable, params));
            return response;
        } finally {
            marsCurtain.complete(request, response, params);
        }
    }

    public BaseMarsResponse generateNonEmptyResults(BaseMarsRequest request, BaseMarsResponse baseMarsResponse) {
        return Optional.ofNullable(baseMarsResponse).orElse(marsCurtain.defaultHandler(request));
    }
}
