package top.auzqy.comment.common;

import org.springframework.validation.BindingResult;

/**
 * description:  通用工具类封装
 * createTime: 2019-12-28 14:14
 * @author au
 */
public class CommonUtil {
    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        bindingResult.getFieldErrors().forEach(
                fieldError ->
                        sb.append(fieldError.getDefaultMessage())
                                .append(",")
        );
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
