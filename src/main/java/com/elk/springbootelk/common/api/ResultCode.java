package com.elk.springbootelk.common.api;

/**
 * @author zmf
 * @version 1.0
 * @ClassName ResultCode
 * @Description: 枚举类,定义了具体的异常信息和状态码
 * @date 2020/3/9 13:39
 */
public enum ResultCode implements IErrorCode{

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
