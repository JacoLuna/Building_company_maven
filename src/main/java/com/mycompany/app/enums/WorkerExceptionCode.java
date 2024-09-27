package com.mycompany.app.enums;

public enum WorkerExceptionCode {
    EXISTING_WORKER(0);

    public final int codeNumber;

    WorkerExceptionCode(int codeNumber){
        this.codeNumber = codeNumber;
    }

    public String getError(){
        StringBuilder sb = new StringBuilder();
        switch (codeNumber){
            case 0 -> sb.append("This worker already exists");
        }
        return sb.toString();
    }
}
