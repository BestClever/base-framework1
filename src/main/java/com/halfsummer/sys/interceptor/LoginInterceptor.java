package com.halfsummer.sys.interceptor;

import com.halfsummer.config.RequestHolder;
import com.halfsummer.sys.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author BestClever
 * @title: LoginInterceptor
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-23 23:38
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "requestStartTime";

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        User user = (User) session.getAttribute("user");
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        //如果session中没有user，表示没登陆
        if (user == null){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            response.sendRedirect("/sys/toLogin");
            return false;
        }else {
            RequestHolder.add(user);
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }


    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    /*请求结束之后调用,正常和异常都会调用*/
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request finished. url:{},cost:{}",url,end-start);
        removeThreadLocalInfo();
    }

    public void removeThreadLocalInfo(){
        RequestHolder.remove();
    }
}
