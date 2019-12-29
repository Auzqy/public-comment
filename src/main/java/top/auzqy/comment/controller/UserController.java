package top.auzqy.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.auzqy.comment.common.CommonRes;
import top.auzqy.comment.common.CommonUtil;
import top.auzqy.comment.common.EmBusinessError;
import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.model.UserModel;
import top.auzqy.comment.request.LoginReq;
import top.auzqy.comment.request.RegisterReq;
import top.auzqy.comment.service.IUserService;

import javax.servlet.http.HttpServletRequest;
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

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

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

    /**
     * todo 眼下的这种方式，如果用于在不同的浏览器登录，退出时也需要分别退出
     *      正确的方式因该登录时判断同一用户，
     *      登出时判断时谁登出
     *      获取当前用户也应该传递参数
     *
     * @param loginReq
     * @param bindingResult
     * @return
     * @throws NoSuchAlgorithmException
     * @throws BusinessException
     */
    @PostMapping("/login")
    @ResponseBody
    public CommonRes login(@Valid @RequestBody LoginReq loginReq,
                           BindingResult bindingResult)
            throws NoSuchAlgorithmException, BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = loginReq.convert2UserModel();
        UserModel loginUser = userService.login(userModel);

        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,loginUser);

        return CommonRes.success(loginUser);
    }


    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logout() {
        httpServletRequest.getSession().invalidate();
        return CommonRes.success(new UserModel());
    }

    @RequestMapping("/getcurrentuser")
    @ResponseBody
    public CommonRes getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession()
                .getAttribute(CURRENT_USER_SESSION);
        return CommonRes.success(userModel);
    }
}
