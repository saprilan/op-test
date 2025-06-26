package com.sapril.op.model;

import java.util.List;

public class ValidationResult {
    private List<Deviation> deviations;
    private ParsedEfakturData validatedData;

    public ValidationResult(List<Deviation> deviations, ParsedEfakturData validatedData) {
        this.deviations = deviations;
        this.validatedData = validatedData;
    }

    public List<Deviation> getDeviations() {
        return deviations;
    }

    public void setDeviations(List<Deviation> deviations) {
        this.deviations = deviations;
    }

    public ParsedEfakturData getValidatedData() {
        return validatedData;
    }

    public void setValidatedData(ParsedEfakturData validatedData) {
        this.validatedData = validatedData;
    }
}
