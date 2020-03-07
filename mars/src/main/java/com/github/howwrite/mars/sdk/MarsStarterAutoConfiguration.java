package com.github.howwrite.mars.sdk;

import com.github.howwrite.mars.sdk.config.MarsProperties;
import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.filter.MarsFilter;
import com.github.howwrite.mars.sdk.support.MarsResolver;
import com.github.howwrite.mars.sdk.support.MarsReturnValueHandler;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
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
public class MarsStarterAutoConfiguration implements WebMvcConfigurer {

    @Autowired(required = false)
    private AbstractResourceBasedMessageSource abstractResourceBasedMessageSource;

    @Resource
    private WxUtils wxUtils;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MarsResolver(wxUtils));
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new MarsReturnValueHandler(wxUtils));
    }

    @PostConstruct
    public void registerMessageSource() {
        if (!ObjectUtils.isEmpty(abstractResourceBasedMessageSource)) {
            abstractResourceBasedMessageSource.getBasenameSet().add("mars-messages");
        }
    }

    @Bean
    public FilterRegistrationBean<MarsFilter> aclAuthFilter(MarsProperties marsProperties, WxUtils wxUtils) {
        FilterRegistrationBean<MarsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MarsFilter(wxUtils));
        filterRegistrationBean.addUrlPatterns(marsProperties.getPath());
        filterRegistrationBean.setOrder(-10);
        return filterRegistrationBean;
    }
}
