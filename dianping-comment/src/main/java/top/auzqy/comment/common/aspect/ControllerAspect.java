package top.auzqy.comment.common.aspect;

import top.auzqy.comment.common.CommonError;
import top.auzqy.comment.common.CommonRes;
import top.auzqy.comment.common.EmBusinessError;
import top.auzqy.comment.common.annotation.AdminPermission;
import top.auzqy.comment.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class ControllerAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Around("execution(* top.auzqy.comment.controller.admin.*.*(..)) " +
            "&& @annotation(" +
            "org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(
            ProceedingJoinPoint joinPoint)
            throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if(adminPermission == null){
            //公共方法
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
        //判断当前管理员是否登录
        String email = (String) httpServletRequest.getSession()
                .getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if(email == null){
            if("text/html".equals(adminPermission.produceType())){
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            }else{
                CommonError commonError= new CommonError(
                        EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.fail(commonError);
            }

        }else{
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
    }
}
