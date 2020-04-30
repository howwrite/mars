package com.github.howwrite.mars.integration.demo.handler;

import com.github.howwrite.mars.integration.support.MarsMessageHandler;
import com.github.howwrite.mars.sdk.facade.MarsResponseFactory;
import com.github.howwrite.mars.sdk.facade.MarsWxUtils;
import com.github.howwrite.mars.sdk.info.TempResourceInfo;
import com.github.howwrite.mars.sdk.request.MarsImageRequest;
import com.github.howwrite.mars.sdk.request.MarsTextRequest;
import com.github.howwrite.mars.sdk.request.MarsVoiceRequest;
import com.github.howwrite.mars.sdk.response.BaseMarsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;

/**
 * @author howwrite
 * @date 2020/4/5 下午10:15:42
 */
@Order(-1)
@Component
@Slf4j
@RequiredArgsConstructor
public class OneHandler implements MarsMessageHandler {
    private final MarsWxUtils marsWxUtils;

    @Override
    public BaseMarsResponse doText(MarsTextRequest request) {
        return MarsResponseFactory.createTextResponse(request, "i am one");
    }

    @Override
    public BaseMarsResponse doVoice(MarsVoiceRequest request) throws Throwable {
        return MarsResponseFactory.createTextResponse(request, request.getRecognition());
    }

    /**
     * 接受图片消息，并保存图片
     *
     * @param request 图片消息内容
     * @return 保存成功则返回文件名，保存失败则返回错误信息
     * @throws Throwable error
     */
    @Override
    public BaseMarsResponse doImage(MarsImageRequest request) throws Throwable {
        TempResourceInfo tempMediaImage = marsWxUtils.getTempResource(request.getMediaId());
        if (tempMediaImage.getSuccess()) {
            FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(new File(tempMediaImage.getFileName()));
            byte[] bytes = tempMediaImage.getBytes();
            fileImageOutputStream.write(bytes, 0, bytes.length);
            fileImageOutputStream.close();
            return MarsResponseFactory.createTextResponse(request, tempMediaImage.getFileName());
        } else {
            log.warn("get temp res error", tempMediaImage.getThrowable());
            return MarsResponseFactory.createTextResponse(request, tempMediaImage.getErrorMsg());
        }
    }

}