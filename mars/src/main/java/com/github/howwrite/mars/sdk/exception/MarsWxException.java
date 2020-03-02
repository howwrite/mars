package com.github.howwrite.mars.sdk.exception;

/**
 * 微信相关的报错
 *
 * @author howwrite
 * @date 2019/12/29 下午8:53:50
 */
public class MarsWxException extends MarsException {
    public MarsWxException(String code) {
        super(code);
    }

    public MarsWxException(String code, String message) {
        super(code, message);
    }

    public MarsWxException(String code, Throwable throwable) {
        super(code, throwable);
    }
}
