package com.mycompany.app.classes.Exceptions;

import com.mycompany.app.enums.ProjectListExceptionCode;


public class ProjectListException extends Exception{
    public ProjectListException(int exceptionCode){
        super(ProjectListExceptionCode.values()[exceptionCode].message);
    }
}
