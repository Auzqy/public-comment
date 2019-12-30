package top.auzqy.comment.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description:  统一的错误对象
 * createTime: 2019-12-28 10:58
 * @author au
 */
@Data
@AllArgsConstructor
public class CommonError {
    /**
     * 错误码
     */
    private Integer errCode;

    /**
     * 错误描述
     */
    private String errMsg;

    public CommonError(EmBusinessError emBusinessError){
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }
}