package top.auzqy.comment.common.exception;

import lombok.Data;
import top.auzqy.comment.common.CommonError;
import top.auzqy.comment.common.EmBusinessError;

/**
 * description:  统一的业务异常
 * createTime: 2019-12-28 11:31
 * @author au
 */
@Data
public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError){
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public BusinessException(EmBusinessError emBusinessError,String errMsg){
        super();
        this.commonError = new CommonError(emBusinessError);
        this.commonError.setErrMsg(errMsg);
    }
}