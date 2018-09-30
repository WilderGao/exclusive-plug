package com.qg.exclusiveplug.service.impl;

import com.qg.exclusiveplug.cache.CacheMap;
import com.qg.exclusiveplug.enums.StateEnum;
import com.qg.exclusiveplug.model.Device;
import com.qg.exclusiveplug.service.TcpService;
import com.qg.exclusiveplug.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WilderGao
 * time 2018-09-23 10:37
 * motto : everything is no in vain
 * description
 */
@Service
@Slf4j
public class TcpServiceImpl implements TcpService {
    private static final String CACHE_KEY = "devices";

    @Override
    public int messageHandler(String message) {
        List<Device> devices = analysisMessage(message);
        //将数据存放到Redis中进行缓存
        // TODO: 2018/9/26 0026  现在Redis不稳定，先换Map做缓存
//        if (!redisTemplate.hasKey(CACHE_KEY)) {
//            //不存在这个缓存
//            redisTemplate.opsForList().rightPushAll(CACHE_KEY, devices);
//        }else {
//            List<Device> devicesCache = (List) redisTemplate.opsForList().leftPop(CACHE_KEY);
//            devicesCache.addAll(devices);
//            redisTemplate.opsForList().rightPushAll(CACHE_KEY, devices);
//        }
//        return StateEnum.OK.getState();

        if (!CacheMap.containKey(CACHE_KEY)){
            CacheMap.put(CACHE_KEY, devices);
        }else {
            CacheMap.get(CACHE_KEY).addAll(devices);
        }
        return StateEnum.OK.getState();

    }

    /**
     * 将tcp收到的消息解析成设备对象
     * @param message   消息
     * @return  设备对象
     */
    private List<Device> analysisMessage(String message){
        //解析参数
        String[] parameters = message.split("end");
        List<String> list = Arrays.asList(parameters);
        List<Device> devices = new LinkedList<>();
        for(String s : list){
            //查看是哪个串口
            int index = (int)s.charAt(s.length()-1) - 48;
            s = s.substring(0, s.length()-1);
            log.info(s);
            //得到单个插口所有参数信息
            String[] plugs = s.split(",");
            String name = plugs[0].split(":")[0];
            double voltage = Double.parseDouble(plugs[0].split(":")[2]);
            double current = Double.parseDouble(plugs[1].split(":")[1]);
            double power = Double.parseDouble(plugs[2].split(":")[1]);
            double powerFactor = Double.parseDouble(plugs[3].split(":")[1]);
            double frequency = Double.parseDouble(plugs[4].split(":")[1]);
            double cumulativePower = Double.parseDouble(plugs[5].split(":")[1]);
            String currentTime = DateUtil.currentTime();

            Device device = new Device(index, name, current, voltage, power, powerFactor, frequency, currentTime, cumulativePower);
            System.out.println(device.toString());
            devices.add(device);
        }

        return devices;
    }
}
