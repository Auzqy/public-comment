package top.auzqy.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.auzqy.comment.model.UserModel;
import top.auzqy.comment.service.IUserService;

/**
 * description:
 * createTime: 2019-12-26 23:35
 * @author au
 */
@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/get")
    @ResponseBody
    public UserModel getUser(@RequestParam("id") Integer id) {
        return userService.getUser(id);
    }
}
