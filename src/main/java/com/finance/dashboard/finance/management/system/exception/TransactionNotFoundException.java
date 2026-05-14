package com.finance.dashboard.finance.management.system.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException (String message){
        super(message);
    }

}
