package com.github.howwrite.mars.sdk;

import com.github.howwrite.mars.sdk.config.MarsProperties;
import com.github.howwrite.mars.sdk.config.MarsWxProperties;
import com.github.howwrite.mars.sdk.filter.MarsFilter;
import com.github.howwrite.mars.sdk.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;

/**
 * @author howwrite
 * @Description 自动装配mars所需的bean
 * @create 2019/12/15 15:38
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties({MarsProperties.class, MarsWxProperties.class})
public class MarsStarterAutoConfiguration {

    @Autowired(required = false)
    private AbstractResourceBasedMessageSource abstractResourceBasedMessageSource;

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
