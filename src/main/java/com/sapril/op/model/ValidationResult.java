package com.sapril.op.model;

import java.util.List;

public class ValidationResult {
    private List<Deviation> deviations;
    private ParsedEfakturData validated_data;

    public ValidationResult(List<Deviation> deviations, ParsedEfakturData validated_data) {
        this.deviations = deviations;
        this.validated_data = validated_data;
    }

    public List<Deviation> getDeviations() {
        return deviations;
    }

    public void setDeviations(List<Deviation> deviations) {
        this.deviations = deviations;
    }

    public ParsedEfakturData getValidated_data() {
        return validated_data;
    }

    public void setValidated_data(ParsedEfakturData validated_data) {
        this.validated_data = validated_data;
    }
}
