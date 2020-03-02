package com.github.howwrite.mars.sdk.exception;

/**
 * @author howwrite
 * @Description:
 * @create 2019/12/15 21:09
 */
public class MarsException extends RuntimeException {
    private String message;

    private String code;

    private Throwable throwable;

    public MarsException(String code, String message) {
        super(code);
        this.code = code;
        this.message = message;
    }

    public MarsException(String code) {
        super(code);
        this.code = code;
    }

    public MarsException(String code, Throwable throwable) {
        this.code = code;
        this.throwable = throwable;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
