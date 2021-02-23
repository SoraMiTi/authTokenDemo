package com.example.demo.framework.constant;


import com.example.demo.framework.model.EnumEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2020/7/28]
 **/
public abstract class BaseEnumEntity {
    /**
     * 还想用静态方法，则需要通过class来获取对象。
     * <p>
     * 列出所有字段
     * <pre>
     *  SysRole.listAllField(SysRole.class)
     * </pre>
     *
     * @return {@link List < EnumEntity >}
     */
    public static List<EnumEntity> listAllField(Class<?> clazz) {
        List<EnumEntity> resultList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(clazz);
                if (o instanceof EnumEntity) {
                    resultList.add((EnumEntity) o);
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * 获取字段名
     * <pre>
     *  SysRole.getFieldName(SysRole.class, SysRole.SUPPLIER)
     * </pre>
     *
     * @param code code
     * @return {@link String}
     */
    public static String getFieldName(Class<?> clazz, String code) {
        List<EnumEntity> statusEntities = listAllField(clazz);
        for (EnumEntity statusEntity : statusEntities) {
            if (code.equals(statusEntity.getCode())) {
                return statusEntity.getName();
            }
        }
        return null;
    }
    /**
     * 获取字段名
     * <pre>
     *  SysRole.getFieldName(SysRole.class, SysRole.SUPPLIER)
     * </pre>
     *
     * @param code code
     * @return {@link String}
     */
    public static String getFieldName(Class<?> clazz, Integer code) {
        return getFieldName(clazz, code.toString());
    }

}
