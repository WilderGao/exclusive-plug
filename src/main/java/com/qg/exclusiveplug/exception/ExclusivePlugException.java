package com.qg.exclusiveplug.exception;

import com.qg.exclusiveplug.enums.StateEnum;

/**
 * @author WilderGao
 * time 2018-09-23 11:08
 * motto : everything is no in vain
 * description
 */
public class ExclusivePlugException extends Exception{
    private StateEnum stateEnum;

    public ExclusivePlugException(StateEnum stateEnum){
        this.stateEnum = stateEnum;
    }
}
