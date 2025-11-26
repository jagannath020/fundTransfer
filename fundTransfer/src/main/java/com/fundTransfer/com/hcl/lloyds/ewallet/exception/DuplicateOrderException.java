package com.fundTransfer.com.hcl.lloyds.ewallet.exception;

public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException(String msg) {
        super(msg);
    }
}
