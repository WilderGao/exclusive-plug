package com.qg.exclusiveplug.service.impl;

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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public int messageHandler(String message) {
        Device device = analysisMessage(message);
        //将数据存放到Redis中进行缓存
        if (!redisTemplate.hasKey(CACHE_KEY)) {
            //不存在这个缓存
            List<Device> devices = new LinkedList<>();
            devices.add(device);
            redisTemplate.opsForList().rightPushAll(CACHE_KEY, devices);
        }else {
            List<Device> devices = (List) redisTemplate.opsForList().leftPop(CACHE_KEY);
            devices.add(device);
            redisTemplate.opsForList().rightPushAll(CACHE_KEY, devices);
        }
        return StateEnum.OK.getState();
    }

    /**
     * 将tcp收到的消息解析成设备对象
     * @param message   消息
     * @return  设备对象
     */
    private Device analysisMessage(String message){
        //解析参数
        String[] parameters = message.split(",");
        //设备名称
        String name = parameters[0].split(":")[1];
        //电流
        double current = Double.parseDouble(parameters[1].split(":")[1]);
        //电压
        double voltage = Double.parseDouble(parameters[2].split(":")[1]);
        //功率
        double power = Double.parseDouble(parameters[3].split(":")[1]);
        //时间
        String currentTime = DateUtil.currentTime();

        return new Device(name, current, voltage, power, currentTime);
    }
}
