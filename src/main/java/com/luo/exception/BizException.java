package com.luo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1001L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;


    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
        this.errorMsg = msg;
    }

    public BizException(String msg, String code) {
        super(code);
        this.errorMsg = msg;
        this.errorCode = code;
    }

    public BizException(String msg, String code, Throwable throwable) {
        super(code, throwable);
        this.errorMsg = msg;
        this.errorCode = code;
    }

    public BizException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public BizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

}
