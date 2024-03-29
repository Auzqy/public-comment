package top.auzqy.comment.request;

import lombok.Data;
import top.auzqy.comment.model.UserModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * description:  用于登录的请求对象
 * createTime: 2019-12-28 16:26
 * @author au
 */
@Data
public class LoginReq {
    /**
     * 手机号格式的问题看需要
     * 如果不写 message 的话，就会返回
     * "errMsg":"需要匹配正则表达式\"^[1][\\d]{10}$\""
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][\\d]{10}$",message = "手机号格式不对")
    private String telephone;
    @NotBlank(message = "密码不能为空")
    private String password;

    public UserModel convert2UserModel() {
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setPassword(password);
        return userModel;
    }
}
