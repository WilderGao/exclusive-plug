package com.qg.exclusiveplug.service;

/**
 * @author WilderGao
 * time 2018-09-23 17:59
 * motto : everything is no in vain
 * description
 */
public interface TimeService {
    /**
     * 把缓存在redis中的数据保存到MySQL
     * @return 保存结果
     */
    void saveDataToMySql() throws InterruptedException;
}
