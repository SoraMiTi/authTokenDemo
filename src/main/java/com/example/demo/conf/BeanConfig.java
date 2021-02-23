package com.example.demo.conf;

import com.example.demo.framework.common.SnowFlakeWorker;
import com.example.demo.logic.service.UserTokenGenerator;
import com.example.demo.util.Md5Util;
import com.example.demo.util.RandomUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author youfang
 * @version [1.0.0, 2020-4-4 下午 09:32]
 **/
@Configuration
public class BeanConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    public SnowFlakeWorker snowFlakeWorker(){
        return new SnowFlakeWorker();
    }

    @Bean
    @Profile("!dev")
    public UserTokenGenerator userTokenGenerator(){
        return loginUser -> RandomUtil.randomUuidWithoutLine();
    }

    @Bean
    @Profile("dev")
    public UserTokenGenerator userTokenGeneratorDev(){
        return loginUser -> {
            //方便测试：为admin用户生成固定token
            if ("1".equals(loginUser.getPhone())) {
                return "admin";
            }
            return RandomUtil.randomUuidWithoutLine();
        };
    }
}
