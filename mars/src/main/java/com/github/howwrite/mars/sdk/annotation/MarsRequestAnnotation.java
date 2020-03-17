package com.github.howwrite.mars.sdk.annotation;

import java.lang.annotation.*;

/**
 * 用于将xml字段对应成request字段的注解
 * 标注于set方法，将 `xmlName` 字段的值当做set方法参数并调用方法
 *
 * @author howwrite
 * @date 2020/3/10 下午12:34:55
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MarsRequestAnnotation {

    /**
     * 表示此set方法的参数对应的xml中的标签名
     */
    String xmlName();
}
