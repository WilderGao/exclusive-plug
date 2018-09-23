package com.qg.exclusiveplug.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WilderGao
 * time 2018-09-23 09:53
 * motto : everything is no in vain
 * description 电器设备实体类
 */
@Data
public class Device implements Serializable {
    /**
     * 设备Id
     */
    private int id;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 电流
     */
    private double current;
    /**
     * 电压
     */
    private double voltage;
    /**
     * 功率
     */
    private double power;

    /**
     * 发送记录的日期
     */
    private String date;


    public Device(String name, double current, double voltage, double power, String date) {
        this.name = name;
        this.current = current;
        this.voltage = voltage;
        this.power = power;
        this.date = date;
    }
}
