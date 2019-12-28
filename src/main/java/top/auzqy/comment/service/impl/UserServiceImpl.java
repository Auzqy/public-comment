package top.auzqy.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.auzqy.comment.dao.UserModelMapper;
import top.auzqy.comment.model.UserModel;
import top.auzqy.comment.service.IUserService;

/**
 * description:  
 * createTime: 2019-12-27 19:20
 * @author au
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
