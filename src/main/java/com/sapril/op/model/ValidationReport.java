package com.sapril.op.model;

public class ValidationReport {
    private String status;
    private String message;
    private ValidationResult validation_results;

    public ValidationReport(String status, String message, ValidationResult result) {
        this.status = status;
        this.message = message;
        this.validation_results = result;
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

    public ValidationResult getValidation_results() {
        return validation_results;
    }

    public void setValidation_results(ValidationResult validation_results) {
        this.validation_results = validation_results;
    }
}

