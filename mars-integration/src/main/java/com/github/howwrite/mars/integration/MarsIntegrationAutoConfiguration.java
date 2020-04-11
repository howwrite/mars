package com.github.howwrite.mars.integration;

import com.github.howwrite.mars.integration.support.MarsCurtain;
import com.github.howwrite.mars.integration.support.impl.DefaultMarsCurtain;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author howwrite
 * @date 2020/3/22 下午1:02:53
 */
@Configuration
@ComponentScan
public class MarsIntegrationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public MarsCurtain marsCurtain() {
        return new DefaultMarsCurtain();
    }
}
