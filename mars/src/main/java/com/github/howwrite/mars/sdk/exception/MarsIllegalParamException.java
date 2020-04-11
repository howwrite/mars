package com.github.howwrite.mars.sdk.exception;

/**
 * 非法参数异常
 *
 * @author howwrite
 * @date 2020/4/7 下午7:53:40
 */
public class MarsIllegalParamException extends MarsException {
    private static final long serialVersionUID = 2381440927674495673L;

    public MarsIllegalParamException(String code, Throwable throwable) {
        super(code, throwable);
    }

    public MarsIllegalParamException(String code) {
        super(code);
    }
}
