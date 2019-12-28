package top.auzqy.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.auzqy.comment.common.CommonUtil;
import top.auzqy.comment.common.EmBusinessError;
import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.common.CommonRes;
import top.auzqy.comment.model.UserModel;
import top.auzqy.comment.request.RegisterReq;
import top.auzqy.comment.service.IUserService;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

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

    @PostMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq,
                              BindingResult bindingResult)
            throws BusinessException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = registerReq.convert2UserModel();
        UserModel resUserModel = userService.register(userModel);
        return CommonRes.success(resUserModel);
    }
}
