package com.mall.pay.util;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ckstart
 * @create 2023-03-02 21:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R(int code) {
        this.code = code;
    }

    public static <T> R<T> ok(T data){
        return new R(HttpStatus.HTTP_OK,"success",data);
    }
    public static  R ok(){
        return new R(HttpStatus.HTTP_OK,"success",null);
    }
}
