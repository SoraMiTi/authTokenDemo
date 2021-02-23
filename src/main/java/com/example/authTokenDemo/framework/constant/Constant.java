package com.example.authTokenDemo.framework.constant;

import com.example.authTokenDemo.framework.model.EnumEntity;

import java.util.List;

/**
 * @author luwl
 * @version [1.0.0, 2020/4/1]
 **/
public class Constant {
    public static final Integer FLAG_FALSE_0 = 0;
    public static final Integer FLAG_TRUE_1 = 1;

    public static final Integer CONS_0 = 0;
    public static final Long CONS_LONG_0 = 0L;
    public static final Integer CONS_1 = 1;
    public static final Integer CONS_2 = 2;
    public static final Integer CONS_3 = 3;
    public static final Integer CONS_4 = 4;
    public static final Integer CONS_5 = 5;
    public static final Integer CONS_99 = 99;

    public static final Integer NORMAL = 1;
    public static final Integer DISABLED = 0;


    /**
     * 系统使用用户ID
     */
    public static final Long SYSTEM_USER_ID = 1L;
    /**
     * 请求头header名称
     */
    public static final String HEADER_TOKEN_NAME = "Authentication";


    public static class TestEnum extends BaseEnumEntity {
        public static final String test1 = "test1";
        public static final String test2 = "test2";
        public static final String test3 = "test3";

        public static final EnumEntity test1_entity = new EnumEntity("test1", "测试1");
        public static final EnumEntity test2_entity = new EnumEntity("test2", "测试2");
        public static final EnumEntity test3_entity = new EnumEntity("test2", "测试2");
    }

    public static void main(String[] args) {
        //获取所有静态值
        List<EnumEntity> statusEntities = Constant.TestEnum.listAllField(Constant.TestEnum.class);
        statusEntities.stream().forEach(e -> System.out.println(e.getName() + "  " + e.getCode()));
        //根据code获取name
        System.err.println(TestEnum.getFieldName(Constant.TestEnum.class, TestEnum.test1));
    }

}

