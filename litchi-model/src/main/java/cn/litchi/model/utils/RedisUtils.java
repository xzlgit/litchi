package cn.litchi.model.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    private RedisTemplate<String, Serializable> redisTemplate;

    public RedisUtils(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 保存数据
     */
    public void saveData(String key, Serializable data, int time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, data, time, unit);
    }

    public void saveData(String key, Serializable data) {
        redisTemplate.opsForValue().set(key, data);
    }

    public Serializable getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean isExit(String key) {
        return redisTemplate.opsForValue().get(key) != null;
    }

    /**
     * 删除缓存
     */
    public void deleteCache(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        if (keys == null || keys.size() == 0) return;
        redisTemplate.delete(keys);
    }

}
