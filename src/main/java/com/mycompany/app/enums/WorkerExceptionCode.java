package com.mycompany.app.enums;

public enum WorkerExceptionCode {
    EXISTING_WORKER(0,"This worker already exists");

    public final int codeNumber;
    public final String message;

    WorkerExceptionCode(int codeNumber, String message){
        this.codeNumber = codeNumber;
        this.message = message;
    }
}
