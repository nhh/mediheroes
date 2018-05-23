package com.mediheroes.mediheroes.job;

import io.lettuce.core.RedisClient;
import org.springframework.scheduling.annotation.Scheduled;

public class SessionFlushJob {

    private final RedisClient redisClient;

    public SessionFlushJob(
        RedisClient redisClient
    ){
        this.redisClient = redisClient;
    }

    // Todo implement jobs like this:
    //@Scheduled(cron = "* * * * * *")
    private void clearSessionStorage(){
        redisClient.connect().async().flushdb();
    }

}
