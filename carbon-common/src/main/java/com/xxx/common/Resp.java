package com.xxx.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义返回对象模板
 * @param <T>
 */
@Data
public class Resp<T> {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> Resp<T> success(T object) {
        Resp<T> r = new Resp<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Resp<T> error(String msg) {
        Resp r = new Resp();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public Resp<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
