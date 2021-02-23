package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;

/**
 * @author luwl
 * @version [1.0.0, 2020/7/28]
 **/
public class FastJsonUtil {

    /**
     * 使用JSON的方式，将map转换为bean
     *
     * @param map   map
     * @param clazz bean
     * @return clazz
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        String jsonStr = JSON.toJSONString(map);
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * 使用JSON的方式，将bean转换为map
     *
     * @param obj bean
     * @return MAP
     */
    public static <T> Map<String, String> beanToMap(Object obj) {
        String jsonStr = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, String>>() {
        });
    }

    /**
     * 使用JSON的方式，将bean转换为map
     *
     * @param obj bean
     * @return MAP
     */
    public static <T> Map<String, Object> beanToObjectMap(Object obj) {
        String jsonStr = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 使用JSON的方式，将JSON字符串转换为MAP
     *
     * @param jsonStr JSON字符串
     * @return MAP
     */
    public static <T> Map<String, String> jsonStrToMap(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, String>>() {
        });
    }


    /**
     * 将json字符串转换为Bean
     *
     * @param jsonStr jsonStr
     * @param tClass  类型
     * @return {@link T}
     */
    public static <T> T parseObject(String jsonStr, Class<T> tClass) {
        return JSON.parseObject(jsonStr, tClass);
    }

    /**
     * bean 转换为 json 字符串（保留值为null的属性）
     *
     * @param o o
     * @return {@link String}
     */
    public static String beanToJsonStr(Object o) {
        return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue);
    }
}
