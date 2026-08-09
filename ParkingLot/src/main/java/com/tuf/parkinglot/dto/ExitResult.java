package com.tuf.parkinglot.dto;

import com.tuf.parkinglot.domain.Reciept;

public class ExitResult {
    private final boolean success;
    private final Reciept reciept;
    private final String message;

    public ExitResult(boolean success, Reciept reciept, String message) {
        this.success = success;
        this.reciept = reciept;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Reciept getReciept() {
        return reciept;
    }

    public String getMessage() {
        return message;
    }
}
