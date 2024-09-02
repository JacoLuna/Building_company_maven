package com.mycompany.app.enums;

public enum ProjectListExceptionCode {
    EXISTING_PROJECT(0,"This project already exists in the list"),
    NOT_EXISTING_PROJECT(1,"This project does not exist in the list"),
    NOT_EXISTING_INDEX(2,"This index project does not exist in the list"),
    SAME_PROJECT(3,"This project is already at this index"),
    EMPTY_LIST(4,"This list is already empty");
    public final int codeNumber;
    public final String message;
    ProjectListExceptionCode(int codeNumber,String message){
        this.codeNumber = codeNumber;
        this.message = message;
    }
}
