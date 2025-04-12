package com.credit.model;

public class LoanHistory {
    private String sheetName;
    private LoanCalculation data;

    public LoanHistory(String sheetName, LoanCalculation data) {
        this.sheetName = sheetName;
        this.data = data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public LoanCalculation getData() {
        return data;
    }

    public void setData(LoanCalculation data) {
        this.data = data;
    }
}
