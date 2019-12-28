package top.auzqy.comment.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description:  统一响应对象的封装
 * createTime: 2019-12-28 10:46
 * @author au
 */
@Data
@AllArgsConstructor
public class CommonRes {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    /**
     * 表明读经请求的返回处理结果，"success"或"fail"
     */
    private String status;
    /**
     * 若status=success时，表明对应的返回的json类数据
     * 若status=fail时，则data内将使用通用的错误码对应的格式
     */
    private Object data;

    public static CommonRes success(Object result){
        return CommonRes.create(result,SUCCESS);
    }

    public static CommonRes fail(Object result){
        return CommonRes.create(result,FAIL);
    }

    private static CommonRes create(Object result,String status){
        return new CommonRes(status,result);
    }
}
