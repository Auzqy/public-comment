package top.auzqy.comment.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
        } else {
            return CommonRes.fail(
                    new CommonError(
                            EmBusinessError.UNKNOWN_ERROR));
        }
    }
}
