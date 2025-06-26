package com.sapril.op.model;

public class Deviation {
    private String field;
    private String pdfValue;
    private String djpApiValue;
    private String deviationType;

    public Deviation(String field, String pdfValue, String djpApiValue, String deviationType) {
        this.field = field;
        this.pdfValue = pdfValue;
        this.djpApiValue = djpApiValue;
        this.deviationType = deviationType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPdfValue() {
        return pdfValue;
    }

    public void setPdfValue(String pdfValue) {
        this.pdfValue = pdfValue;
    }

    public String getDjpApiValue() {
        return djpApiValue;
    }

    public void setDjpApiValue(String djpApiValue) {
        this.djpApiValue = djpApiValue;
    }

    public String getDeviationType() {
        return deviationType;
    }

    public void setDeviationType(String deviationType) {
        this.deviationType = deviationType;
    }
}
