package com.qg.exclusiveplug.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WilderGao
 * time 2018-09-23 11:09
 * motto : everything is no in vain
 * description
 */
@AllArgsConstructor
public enum  StateEnum {

    /**
     * 状态码
     */
    OK(200, "一切正常"),
    ANALYSIS_ERROR(510, "字符串解析错误");

    @Getter
    @Setter
    private int state;

    @Getter
    @Setter
    private String info;


}
