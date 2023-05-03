package com.blogapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1492743830547334656L;


    private final int errCode;

    public ResourceNotFoundException() {
        super();
        this.errCode = 500;
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.errCode = 500;
    }

    public ResourceNotFoundException(String message, int errCode) {
        super(message);
        this.errCode = errCode;
    }

    public ResourceNotFoundException(Throwable arg0) {
        super(arg0);
        this.errCode = 500;
    }

    public ResourceNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        this.errCode = 500;
    }

    public ResourceNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
        this.errCode = 500;
    }

    /**
     * @return the errCode
     */
    public int getErrCode() {
        return errCode;
    }
}