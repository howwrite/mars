package com.github.howwrite.mars.sdk.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

/**
 * 异常
 *
 * @author howwrite
 * @date 2019/12/29 下午8:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MarsException extends RuntimeException {
    private static final long serialVersionUID = -3016672510022120965L;
    /**
     * 异常参数
     */
    private Object[] args;

    public MarsException(String message) {
        super(message);
    }

    public MarsException(String message, Object... args) {
        this(message);
        this.args = args;
    }

    public MarsException(String message, Throwable throwable, Object... args) {
        this(message, throwable);
        this.args = args;
    }

    public MarsException(String code, Throwable throwable) {
        super(code, throwable);
    }

    @Override
    public String getMessage() {
        String superMessage = super.getMessage();
        return MessageFormat.format(superMessage, args);
    }
}
