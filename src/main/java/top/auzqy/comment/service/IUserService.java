package top.auzqy.comment.service;

import top.auzqy.comment.model.UserModel;

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
}
