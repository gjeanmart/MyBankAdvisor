package com.gjeanmart.mybankadvisor.security.model;

/**
 * Created by Grégoire JEANMART on 2016-05-14.
 */
public enum RoleEnum {

    MBA_USER			("MBA-USER"),
    MBA_ADMIN			("MBA-ADMIN");

    private String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
