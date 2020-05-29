package com.github.howwrite.mars.sdk;

import com.github.howwrite.mars.sdk.config.*;
import com.github.howwrite.mars.sdk.filter.MarsFilter;
import com.github.howwrite.mars.sdk.support.MarsResolver;
import com.github.howwrite.mars.sdk.support.MarsReturnValueHandler;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author howwrite
 * @Description 自动装配mars所需的bean
 * @create 2019/12/15 15:38
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties({MarsProperties.class, MarsWxProperties.class})
@ImportAutoConfiguration({MarsRedisConfiguration.class, MarsJacksonConfiguration.class, MarsFastjsonConfiguration.class, MarsDefaultConfiguration.class})
public class MarsStarterAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private WxUtils wxUtils;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new MarsResolver(wxUtils));
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(0, new MarsReturnValueHandler(wxUtils));
    }

    @Bean
    public FilterRegistrationBean<MarsFilter> marsFilter(MarsProperties marsProperties) {
        FilterRegistrationBean<MarsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MarsFilter(wxUtils));
        filterRegistrationBean.addUrlPatterns(marsProperties.getPath());
        filterRegistrationBean.setOrder(MarsFilter.ORDER);
        return filterRegistrationBean;
    }
}
