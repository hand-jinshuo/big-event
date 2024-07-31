package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest//如果测试类加这个注解 在测试类加载前 会先初始化容器 这样就可以从容器中拿东西了
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        //向redis存储一个键值对
        ValueOperations<String, String> Operations = stringRedisTemplate.opsForValue();
        Operations.set("username", "chiyu1");
        Operations.set("id","1",15, TimeUnit.SECONDS);
    }
    @Test
    public void testGet(){
        //从redis中获取一个键值对
        ValueOperations<String, String> Operations = stringRedisTemplate.opsForValue();
        System.out.println(Operations.get("username"));
    }
}
