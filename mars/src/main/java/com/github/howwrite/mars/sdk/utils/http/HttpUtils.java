package com.github.howwrite.mars.sdk.utils.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.function.Function;

/**
 * @author zhu.senlin
 * @date 2020/4/16 下午8:13:41
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HttpUtils {
    private static final MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public <T> T post(String url, Object body, Class<T> clazz) {

        return null;
    }

    public <T> T get(String url, Map<String, Object> queryParams, Function<Response, T> successFunction, Function<Exception, T> exceptionTFunction) {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        if (!CollectionUtils.isEmpty(queryParams)) {
            queryParams.forEach((key, value) -> httpUrlBuilder.addQueryParameter(key, value.toString()));
        }
        Request httpRequest = new Request.Builder()
                .url(httpUrlBuilder.build())
                .build();
        log.debug("http get request, url:{}, queryParams:{}", url, queryParams);
        try (Response response = CLIENT.newCall(httpRequest).execute()) {
            return successFunction.apply(response);
        } catch (Exception e) {
            return exceptionTFunction.apply(e);
        }
    }
}
