package com.github.howwrite.mars.sdk.exception;

/**
 * 异常
 * {@link MarsErrorCode}
 *
 * @author howwrite
 * @date 2019/12/29 下午8:53:50
 */
public class MarsException extends RuntimeException {
    private static final long serialVersionUID = -3016672510022120965L;

    public MarsException(String message) {
        super(message);
    }

    public MarsException(String code, Throwable throwable) {
        super(code, throwable);
    }
}
