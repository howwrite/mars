package com.github.howwrite.mars.sdk;

import com.github.howwrite.mars.sdk.config.MarsProperties;
import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.facade.MarsCacheExtend;
import com.github.howwrite.mars.sdk.facade.MarsJsonHandler;
import com.github.howwrite.mars.sdk.facade.MarsWxUtils;
import com.github.howwrite.mars.sdk.facade.impl.cache.MarsCacheExtendImpl;
import com.github.howwrite.mars.sdk.facade.impl.cache.MarsRedisCache;
import com.github.howwrite.mars.sdk.facade.impl.json.JacksonJsonHandler;
import com.github.howwrite.mars.sdk.filter.MarsFilter;
import com.github.howwrite.mars.sdk.support.MarsResolver;
import com.github.howwrite.mars.sdk.support.MarsReturnValueHandler;
import com.github.howwrite.mars.sdk.utils.http.HttpUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author howwrite
 * @Description 自动装配mars所需的bean
 * @create 2019/12/15 15:38
 */
@Configuration
@EnableConfigurationProperties({MarsProperties.class, MarsWxProperties.class})
public class MarsStarterAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new MarsResolver());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(0, new MarsReturnValueHandler());
    }

    @Bean
    public FilterRegistrationBean<MarsFilter> marsFilter(MarsProperties marsProperties) {
        FilterRegistrationBean<MarsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MarsFilter());
        filterRegistrationBean.addUrlPatterns(marsProperties.getPath());
        filterRegistrationBean.setOrder(MarsFilter.ORDER);
        return filterRegistrationBean;
    }

    @Bean
    @ConditionalOnMissingBean(MarsCacheExtend.class)
    @ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate")
    public MarsCacheExtend accessTokenRedisCache() {
        return new MarsRedisCache();
    }

    @Bean
    @ConditionalOnMissingBean(MarsCacheExtend.class)
    public MarsCacheExtend accessTokenCacheExtendImpl() {
        return new MarsCacheExtendImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MarsJsonHandler.class)
    public MarsJsonHandler jacksonJsonHandler() {
        return new JacksonJsonHandler();
    }

    @Bean
    public MarsWxUtils marsWxUtils() {
        return new MarsWxUtils();
    }

    @Bean
    public HttpUtils httpUtils() {
        return new HttpUtils();
    }
}
