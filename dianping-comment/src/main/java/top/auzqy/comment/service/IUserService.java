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
     * @throws BusinessException
     * @throws NoSuchAlgorithmException
     */
    UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException;

    /**
     * description:  用户登录
     * createTime: 2019-12-28 16:14
     * @author au
     * @param loginUser
     * @return
     */
    UserModel login(UserModel loginUser) throws BusinessException, NoSuchAlgorithmException;

    /**
     * description:  统计所有用户的数量
     * createTime: 2019-12-28 23:22
     * @author au
     * @return
     */
    Integer countAllUser();
}
