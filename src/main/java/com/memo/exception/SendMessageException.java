package com.memo.exception;

public class SendMessageException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3343203619876090223L;

    public SendMessageException(String message) {
        super(message);
    }

    public SendMessageException() {
        super();
    }

    public SendMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendMessageException(Throwable cause) {
        super(cause);
    }

}
