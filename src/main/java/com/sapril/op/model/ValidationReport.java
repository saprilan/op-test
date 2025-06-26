package com.sapril.op.model;

public class ValidationReport {
    private String status;
    private String message;
    private ValidationResult validationResults;

    public ValidationReport(String status, String message, ValidationResult result) {
        this.status = status;
        this.message = message;
        this.validationResults = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidationResult getValidationResults() {
        return validationResults;
    }

    public void setValidationResults(ValidationResult validationResults) {
        this.validationResults = validationResults;
    }
}

