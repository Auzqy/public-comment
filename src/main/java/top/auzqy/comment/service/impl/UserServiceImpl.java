package top.auzqy.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;
import top.auzqy.comment.common.EmBusinessError;
import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.dao.UserModelMapper;
import top.auzqy.comment.model.UserModel;
import top.auzqy.comment.service.IUserService;

import javax.xml.stream.events.Characters;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException {
        registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
        Date nowTime = new Date();
        registerUser.setCreatedAt(nowTime);
        registerUser.setUpdatedAt(nowTime);
        try {
            userModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }
        UserModel user = getUser(registerUser.getId());
        user.setPassword("");
        return user;
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(md5.digest(
                str.getBytes(StandardCharsets.UTF_8)));
    }
}
