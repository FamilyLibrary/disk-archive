package com.alextim;

public enum Gender {
    NOT_KOWN(0),
    MALE(1),
    FEMALE(2),
    NOT_APPLICABLE(9);

    private final int code;

    public int getCode() {
        return code;
    }

    private Gender(final int code) {
        this.code = code;
    }
}
