package com.github.howwrite.mars.sdk.utils.http;

import com.github.howwrite.mars.sdk.exception.MarsJsonException;
import com.github.howwrite.mars.sdk.facade.MarsJsonHandler;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.function.Function;

/**
 * @author howwrite
 * @date 2020/4/16 下午8:13:41
 */
@Slf4j
public class HttpUtils {
    private static final MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient CLIENT = new OkHttpClient();
    @Resource
    private MarsJsonHandler marsJsonHandler;


    public <T> T post(String url, Object body, Map<String, String> headers, Function<Response, T> successFunction, Function<Exception, T> exceptionFunction) {
        //构造请求参数
        String json;
        try {
            json = marsJsonHandler.toJsonString(body);
        } catch (MarsJsonException e) {
            return exceptionFunction.apply(e);
        }
        RequestBody requestBody = RequestBody.create(JSON_TYPE, json);
        //构造请求
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(requestBody);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(builder::header);
        }
        Request httpRequest = builder
                .build();
        try (Response response = CLIENT.newCall(httpRequest).execute()) {
            return successFunction.apply(response);
        } catch (Exception e) {
            return exceptionFunction.apply(e);
        }
    }

    public <T> T get(String url, Map<String, Object> queryParams, Function<Response, T> successFunction, Function<Exception, T> exceptionFunction) {
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
            return exceptionFunction.apply(e);
        }
    }
}
