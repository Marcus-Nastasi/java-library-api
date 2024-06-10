package com.library.app.Enums.Members;

public enum MemberType {

    STUDENT("student"), REGULAR("regular");

    private final String memberType;

    MemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType() {
        return memberType;
    }
}


