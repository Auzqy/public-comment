package top.auzqy.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:
 * createTime: 2019-12-26 23:35
 * @author au
 */
@Controller("user")
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
