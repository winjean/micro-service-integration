package com.winjean.enums;

public enum ConstantDataStatus {

    DELETED("已删除",0),
    NORMAL("正常",1);

    private final String label;
    private final Integer value;

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    ConstantDataStatus(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
