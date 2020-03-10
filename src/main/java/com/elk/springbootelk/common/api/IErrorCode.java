package com.elk.springbootelk.common.api;

/**
 * 封装API的错误码、消息的获取方式
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}