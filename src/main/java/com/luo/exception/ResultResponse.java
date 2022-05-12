package com.luo.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 自定义response
 */
@Data
public class ResultResponse {

    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;


    public ResultResponse() {

    }

    public ResultResponse(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ResultResponse(BaseErrorInfoInterface errorInfoInterface) {
        this.code = errorInfoInterface.getResultCode();
        this.message = errorInfoInterface.getResultMsg();
    }

    /**
     * 成功且带数据
     *
     * @param data
     * @return
     */
    public static ResultResponse success(Object data) {
        ResultResponse response = new ResultResponse();
        response.setResult(data);
        response.setCode(ExceptionEnum.SUCCESS.getResultCode());
        response.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
        return response;
    }

    /**
     * 失败且出现异常
     *
     * @param errorInfoInterface
     * @return
     */
    public static ResultResponse error(BaseErrorInfoInterface errorInfoInterface) {
        return new ResultResponse(errorInfoInterface);
    }

    /**
     * 单纯的失败
     *
     * @return
     */
    public static ResultResponse error(String code, String msg) {
        return new ResultResponse(code, msg, null);
    }

    /**
     * 拉跨的失败
     *
     * @return
     */
    public static ResultResponse error(String msg) {
        return new ResultResponse("-1", msg, null);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
