package com.soufang.esproject.base;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-16
 */
public enum HouseStatus {
    NOT_AUDITED(0),//未审核
    PASSED(1),//审核通过
    RENTED(2),//已出租
    DELETE(3); //逻辑删除
    private int value;

    HouseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
