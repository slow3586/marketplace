package ru.teamtwo.api.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
