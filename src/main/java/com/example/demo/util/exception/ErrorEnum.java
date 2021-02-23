package com.example.demo.util.exception;

/**
 * @author luwl
 * @version [1.0.0, 2020/5/13]
 **/
public interface ErrorEnum<C extends Enum<?>> {

    /**
     * 返回枚举项的 code
     */
    int code();

    /**
     * 返回枚举项的 message
     */
    String message();

    /**
     * 返回枚举对象
     */
    C get();

}
