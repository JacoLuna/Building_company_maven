package com.mycompany.app.classes.Exceptions;

import com.mycompany.app.enums.WorkerExceptionCode;

public class WorkerException extends Exception {
    public WorkerException(int exceptionCode) {
        super(WorkerExceptionCode.values()[exceptionCode].getError());
    }
}
