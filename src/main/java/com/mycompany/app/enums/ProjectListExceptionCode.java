package com.mycompany.app.enums;

public enum ProjectListExceptionCode {
    EXISTING_PROJECT(0),
    NOT_EXISTING_PROJECT(1),
    NOT_EXISTING_INDEX(2),
    SAME_PROJECT(3),
    EMPTY_LIST(4);
    public final int codeNumber;
    ProjectListExceptionCode(int codeNumber){
        this.codeNumber = codeNumber;
    }

    public String getError(){
        StringBuilder sb = new StringBuilder();
        switch (codeNumber){
            case 0 -> sb.append("This project already exists in the list");
            case 1 -> sb.append("This project does not exist in the list");
            case 2 -> sb.append("This index project does not exist in the list");
            case 3 -> sb.append("This project is already at this index");
            case 4 -> sb.append("This list is already empty");
        }
        return sb.toString();
    }
}
