package top.auzqy.comment.common.exception;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.auzqy.comment.common.CommonError;
import top.auzqy.comment.common.CommonRes;
import top.auzqy.comment.common.EmBusinessError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:  全局异常处理器
 * createTime: 2019-12-28 11:41
 * @author au
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonRes doError(HttpServletRequest request,
                             HttpServletResponse response,
                             Exception ex) {
        if (ex instanceof BusinessException) {
            return CommonRes.fail(
                    ((BusinessException) ex).getCommonError());
        } else if (ex instanceof NoHandlerFoundException) {
            return CommonRes.fail(
                    new CommonError(
                            EmBusinessError.NO_HANDLER_FOUND));
        } else if (ex instanceof ServletRequestBindingException) {
            return CommonRes.fail(
                    new CommonError(
                            EmBusinessError
                                    .REQUEST_PARAM_BIND_EXCEPTION_ERROR));
        } else {
            return CommonRes.fail(
                    new CommonError(
                            EmBusinessError.UNKNOWN_ERROR));
        }
    }
}
