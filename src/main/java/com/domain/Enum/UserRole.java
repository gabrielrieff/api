package com.domain.Enum;

public enum UserRole {
    ADMIN("admin"),
    COMMON("comum");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
