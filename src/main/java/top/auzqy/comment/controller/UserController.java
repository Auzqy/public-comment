package top.auzqy.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.common.CommonRes;
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
    public CommonRes getUser(@RequestParam("id") Integer id)
            throws BusinessException {
        return CommonRes.successMayBeNull(userService.getUser(id));
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        String userName = "templates username";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", userName);
        return modelAndView;
    }
}
