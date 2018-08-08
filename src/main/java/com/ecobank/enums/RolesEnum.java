package com.ecobank.enums;


public enum RolesEnum {

    CSO(1, "ROLE_CSO"),
    DPO(2, "ROLE_DPO"),
    DATA_HANDER(3, "ROLE_DH");


    private final int id;

    private final String roleName;

    RolesEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
