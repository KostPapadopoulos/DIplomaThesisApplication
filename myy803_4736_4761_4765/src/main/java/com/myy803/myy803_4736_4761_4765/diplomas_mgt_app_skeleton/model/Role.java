package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;

public enum Role {
    STUDENT("Student"),
    PROFESSOR("Professor");

    private final String value;

    private Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

