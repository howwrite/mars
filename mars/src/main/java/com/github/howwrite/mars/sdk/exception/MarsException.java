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
        this.message = code;
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
