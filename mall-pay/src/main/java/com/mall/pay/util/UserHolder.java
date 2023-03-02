package com.mall.pay.util;

import com.mall.pay.entity.UserEntity;

/**
 * @author ckstart
 * @create 2023-03-02 22:38
 */
public class UserHolder {
    public static final ThreadLocal<UserEntity> USER_ENTITY_THREAD_LOCAL = new ThreadLocal<>();
    public static UserEntity get(){
        return USER_ENTITY_THREAD_LOCAL.get();
    }

    /**
     * 在请求的拦截器后置中处理
     */
    public static void clear(){
        USER_ENTITY_THREAD_LOCAL.remove();
    }
}
