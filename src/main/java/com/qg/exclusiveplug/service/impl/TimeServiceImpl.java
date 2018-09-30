package com.qg.exclusiveplug.service.impl;

import com.qg.exclusiveplug.cache.CacheMap;
import com.qg.exclusiveplug.dao.DeviceMapper;
import com.qg.exclusiveplug.model.Device;
import com.qg.exclusiveplug.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WilderGao
 * time 2018-09-23 18:03
 * motto : everything is no in vain
 * description
 */
@Service
@Slf4j
public class TimeServiceImpl implements TimeService {
    private static final String CACHE_KEY = "devices";

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    @Async
    @Scheduled(fixedRate = 20000)
    public void saveDataToMySql() throws InterruptedException {
//        if (redisTemplate.hasKey(CACHE_KEY)) {
//            List<Device> devices = (List<Device>) redisTemplate.opsForList().rightPop(CACHE_KEY);
//            log.info("将redis中的数据保存到mysql并删除");
//            deviceMapper.saveDevices(devices);
//        }

        // TODO: 2018/9/26 0026 先改为map缓存
        if (CacheMap.containKey(CACHE_KEY)){
            List<Device> devices = CacheMap.get(CACHE_KEY);
            log.info("将map中的数据保存到mysql并删除");
            deviceMapper.saveDevices(devices);
            CacheMap.remove(CACHE_KEY);
        }
    }
}
