package top.auzqy.comment.request;

import lombok.Data;
import top.auzqy.comment.model.UserModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * description:  注册功能的请求对象
 * createTime: 2019-12-28 14:08
 * @author au
 */
@Data
public class RegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String telephone;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotNull(message = "性别不能为空")
    private Integer gender;


    public UserModel convert2UserModel() {
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setPassword(password);
        userModel.setNickName(nickName);
        userModel.setGender(gender);
        return userModel;
    }
}
