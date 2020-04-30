package com.github.howwrite.mars.sdk.info;

import com.github.howwrite.mars.sdk.utils.JoinerUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhu.senlin
 * @date 2020/4/28 下午3:32:39
 */
@Data
public abstract class BaseMarsInfo implements Serializable {
    private static final long serialVersionUID = 3801776825538530772L;

    private Boolean success = true;
    private String errorMsg;
    private Throwable throwable;

    protected static void fullFailParams(BaseMarsInfo baseMarsInfo, String suffix, String... errorMsg) {
        baseMarsInfo.setSuccess(false);
        baseMarsInfo.setErrorMsg(JoinerUtils.COMMA.join(errorMsg) + suffix);
    }

    protected static void fullFailParams(BaseMarsInfo baseMarsInfo, Throwable throwable, String suffix, String... errorMsg) {
        fullFailParams(baseMarsInfo, suffix, errorMsg);
        baseMarsInfo.setThrowable(throwable);
    }
}
