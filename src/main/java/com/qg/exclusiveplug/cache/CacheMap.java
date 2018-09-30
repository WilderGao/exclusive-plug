package com.qg.exclusiveplug.cache;

import com.qg.exclusiveplug.model.Device;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WilderGao
 * time 2018-09-26 13:17
 * motto : everything is no in vain
 * description
 */
@Slf4j
public class CacheMap {
    private static Map<String, List<Device>> cacheMap = new ConcurrentHashMap<>(4);

    public static List<Device> get(String key){
        if (!key.isEmpty()){
            return cacheMap.get(key);
        }
        log.error("key 有误 "+key);
        return null;
    }

    public static void put(String key, List<Device> value){
        cacheMap.put(key, value);
    }

    public static boolean containKey(String key){
        return cacheMap.containsKey(key);
    }

    public static void remove(String key){
        cacheMap.remove(key);
    }
}
