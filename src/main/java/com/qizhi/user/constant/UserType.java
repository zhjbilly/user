package com.qizhi.user.constant;

public enum UserType {

    STAFF("STAFF"),CUSTOMER("CUSTOMER");
    String type;

    UserType(String type){
       this.type  = type;
    }
}
