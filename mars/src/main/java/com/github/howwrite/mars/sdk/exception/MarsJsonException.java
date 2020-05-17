package com.github.howwrite.mars.sdk.exception;

/**
 * json相关异常
 *
 * @author howwrite
 * @date 2020/5/13 下午10:00:34
 */
public class MarsJsonException extends Exception {
    private static final long serialVersionUID = 5251270953507573920L;

    public MarsJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarsJsonException(Throwable cause) {
        super(cause);
    }
}
