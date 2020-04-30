package com.github.howwrite.mars.sdk.exception;

/**
 * mars加解密相关的异常调用
 *
 * @author zhu.senlin
 * @date 2020/4/26 下午1:50:33
 */
public class MarsEncryptException extends MarsException {
    private static final long serialVersionUID = -3960490321927527476L;

    public MarsEncryptException(String code, Throwable throwable) {
        super(code, throwable);
    }

    public MarsEncryptException(String message) {
        super(message);
    }
}
