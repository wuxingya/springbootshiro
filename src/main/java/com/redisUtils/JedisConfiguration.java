package com.redisUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Component
public class JedisConfiguration {
    @Value("${jedis.port}")
    private int port;
    @Value("${jedis.host}")
    private String host;
    @Value("${jedis.max.total}")
    private Integer maxTotal;
    @Value("${jedis.max.idle}")
    private Integer maxIdle;
    @Value("${jedis.max.waitmillis}")
    private Long maxWaitMillis;
    public  JedisConfiguration() { }

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return  new JedisPool(jedisPoolConfig,host,port);
    }
    //集群使用 这里暂时不用
    //@Bean
    public ShardedJedisPool shardedJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        List<JedisShardInfo> jedisShardInfos = new ArrayList<>();
        //多节点url
//        jedisShardInfos.add(new JedisShardInfo(this.getUrl()));

        return new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);
    }

}
