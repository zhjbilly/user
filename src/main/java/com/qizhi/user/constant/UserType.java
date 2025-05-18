package com.qizhi.user.constant;

public enum UserType {

    STAFF("staff"),CUSTOMER("customer");
    String type;

    UserType(String type){
       this.type  = type;
    }
}
