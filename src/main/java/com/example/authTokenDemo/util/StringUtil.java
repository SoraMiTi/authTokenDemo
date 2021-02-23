package com.example.authTokenDemo.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 补充 String 相关方法
 *
 * @author luwl
 * @version [1.0.0, 2020-7-24 下午 09:31]
 **/
public class StringUtil {
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    /**
     * 换行
     */
    public static final String NEW_LINE = "\r\n";

    /**
     * 拼接重复字符到指定长度
     *
     * <pre>
     * StringUtil.concatLengthChar(4, '9')     = 9999
     * StringUtil.concatLengthChar(4, '0')     = 0000
     * StringUtil.concatLengthChar(5, '8')     = 88888
     * </pre>
     *
     * @param length    长度
     * @param character 字符
     * @return {@link String}
     */
    public static String concatLengthChar(int length, char character) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(character);
        }
        return sb.toString();
    }

    /**
     * 数据库 like %xx%
     *
     * @param value 价值
     * @return {@link String}
     */
    public static String likeLR(String value) {
        return "%" + value + "%";
    }

    /**
     * 字符串加入逗号
     *
     * @param iterable 可迭代的
     * @return {@link String}
     */
    public static String joinOnComma(final Iterable<?> iterable) {
        return StringUtils.join(iterable.iterator(), COMMA);
    }

    /**
     * 格式化模板
     * <pre>
     *         String templateStr= "{姓名}今年{岁}啦！";
     *         Map<String, String> paramMap = new HashMap<>();
     *         paramMap.put("姓名", "王二小");
     *         paramMap.put("岁", "3");
     *         System.out.println(formatTemplate(templateStr, paramMap));//王二小今年3啦！
     *
     * </pre>
     *
     * @param templateStr 模板字符串
     * @param paramsMap   参数
     * @return 格式化后内容
     */
    public static String formatTemplate(String templateStr, Map<String, String> paramsMap) {
        String templateContent = templateStr;
        Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (entry.getValue() == null) {
                continue;
            }
            if (templateContent.indexOf(entry.getKey()) > 0) {
                templateContent = templateContent.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        return templateContent;
    }

    /**
     * 转换字符串
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String toStr(Object obj) {

        String str = "";
        if (obj != null) {
            str = obj.toString();
        }
        return str;
    }

    /**
     * 返回文字内容，如果是null -> "-"
     *
     * @param str str
     * @return {@link String}
     */
    public static String nullAsDash(String str) {
        if (str == null) {
            return "-";
        }
        return str;
    }

    /**
     * 判断字符串是否是空或空串
     *
     * @param str 字符串
     * @return 是否是空或空串
     */
    public static boolean isNullOrBlank(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断list是否为空对象
     *
     * @param list
     * @return 是否是空
     */
    public static boolean isListNull(List<?> list) {
        return list == null || list.isEmpty() || list.size() == 0;
    }


    public static void main(String[] args) {
        String templateStr = "{姓名}今年{岁}啦！";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("姓名", "王二小");
        paramMap.put("岁", "3");
        System.out.println(formatTemplate(templateStr, paramMap));
    }
}
