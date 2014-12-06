package com.memo.exception;

public class MissingComponentException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4086119416164823122L;

    public MissingComponentException(String message) {
        super(message);
    }

    public MissingComponentException() {
        super();
    }

    public MissingComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingComponentException(Throwable cause) {
        super(cause);
    }

}
