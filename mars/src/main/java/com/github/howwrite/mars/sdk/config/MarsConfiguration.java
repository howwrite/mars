package com.github.howwrite.mars.sdk.config;

import com.github.howwrite.mars.sdk.converter.MarsMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author howwrite
 * @date 2020/3/1 下午4:58:23
 */
@Configuration
public class MarsConfiguration implements WebMvcConfigurer {

    @Resource
    private MarsMessageConverter marsMessageConverter;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, marsMessageConverter);
    }
}
