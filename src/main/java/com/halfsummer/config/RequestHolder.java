package com.halfsummer.config;

import com.halfsummer.sys.domain.User;
import com.halfsummer.sys.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BestClever
 * @title: RequestHolder
 * @projectName base-framework
 * @description: 请求缓存类
 * @date 2020-05-25 10:50
 */
public class RequestHolder {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<User>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(User User) {
        userHolder.set(User);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static User getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
