package com.domain.Enum;

public enum RegistrationStatus {
    CONFIRMED("confirmado"),
    CANCELLED("cancelado");

    private String status;

    RegistrationStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
