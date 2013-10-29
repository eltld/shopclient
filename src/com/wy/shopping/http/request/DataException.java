package com.wy.shopping.http.request;

@SuppressWarnings("serial")
public class DataException extends RuntimeException {
    /**
     * Constructs a new {@code RuntimeException} that includes the current stack
     * trace.
     */
    public DataException() {
        super();
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace
     * and the specified detail message.
     * 
     * @param detailMessage
     *            the detail message for this exception.
     */
    public DataException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace,
     * the specified detail message and the specified cause.
     * 
     * @param detailMessage
     *            the detail message for this exception.
     * @param throwable
     *            the cause of this exception.
     */
    public DataException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace
     * and the specified cause.
     * 
     * @param throwable
     *            the cause of this exception.
     */
    public DataException(Throwable throwable) {
        super(throwable);
    }
}
