package com.java.cidade.config.exception;

public class StandardError {

    private Long timeStamp;
    private int status;
    private String error;

    public StandardError() {
        super();
    }

    public StandardError(int status, String error) {
        this.timeStamp = System.currentTimeMillis();
        this.status = status;
        this.error = error;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
