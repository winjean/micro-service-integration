package com.winjean.enums;

public enum ConstantBuildIn {

    USER_DEFINED("自定义数据",0),
    SYSTEM_DEFINED("系统内建数据",1);

    private final String label;
    private final Integer value;

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    ConstantBuildIn(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
