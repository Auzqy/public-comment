package top.auzqy.comment.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * description:  一些统一侧错误对象响应码
 * createTime: 2019-12-28 10:53
 * @author au
 */
@Getter
@AllArgsConstructor
public enum EmBusinessError {

    //通用的错误类型10000开头
    NO_OBJECT_FOUND(10001,"请求对象不存在"),
    UNKNOWN_ERROR(10002,"未知错误"),
    NO_HANDLER_FOUND(10003,"找不到执行的路径操作"),
    BIND_EXCEPTION_ERROR(10004,"请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10005,"请求参数校验失败"),

    //用户服务相关的错误类型20000开头
    REGISTER_DUP_FAIL(20001,"用户已存在"),

    LOGIN_FAIL(20002,"手机号或密码错误"),

    ;

    private Integer errCode;

    private String errMsg;

}