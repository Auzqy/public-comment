package top.auzqy.comment.service;

import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.model.UserModel;

import java.security.NoSuchAlgorithmException;

/**
 * description:
 * createTime: 2019-12-26 23:40
 * @author au
 */
public interface IUserService {

    /**
     * 依据 id 获取用户
     * @param id
     * @return
     */
    UserModel getUser(Integer id);

    /**
     * description:  用户注册
     * createTime: 2019-12-28 13:48
     * @author au
     * @param registerUser
     * @return
     */
    UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException;
}
