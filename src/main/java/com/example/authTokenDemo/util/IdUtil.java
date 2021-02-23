package com.example.authTokenDemo.util;

import com.example.authTokenDemo.util.exception.ServiceException;
import com.example.authTokenDemo.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @author luwl
 * @version [1.0.0, 2020-7-24 下午 08:04]
 **/
@Component
public class IdUtil {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 公共获取单号
     * <pre>
     *  getDatePrefix4BitId("RK")    =   RK2020072700001
     * </pre>
     *
     * @param prefix 前缀
     * @return 单号
     */
    public String getDatePrefixReceiptNo(String prefix) {
        return getDatePrefixId(prefix, 5);
    }

    /**
     * 从Redis中获取ID
     * <p>
     * prefix + 20200724 + 00001
     *
     * @param prefix 前缀
     * @param length 长度
     * @return ID
     */
    public String getDatePrefixId(String prefix, int length) {
        String formatDate = DateUtil.getCurrentDateShortFormat();
        String key = "SEQ:" + formatDate;
        //KEY:  SEQ:20200724                timeOut：36小时(36 * 60 * 60)，销毁一天半之前的Key
        Long increment = redisUtils.increment(key, 1, 129600);
        long maxV = MathUtil.createMaxLong(length);
        if (increment > maxV) {
            throw new ServiceException("ID获取错误：超出最大值!");
        }
        DecimalFormat df = new DecimalFormat(StringUtil.concatLengthChar(length, '0'));
        return prefix + formatDate + df.format(increment);
    }


}
