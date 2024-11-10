package ru.library.api_library.model.Enums;

public enum OperationType {
    BORROW,
    RETURN;

    public static OperationType fromString(String operationType) {
        if (operationType == null)
            throw new IllegalArgumentException("Operation type cannot be null");

        try {
            return OperationType.valueOf(operationType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown operation: " + operationType);
        }
    }
}