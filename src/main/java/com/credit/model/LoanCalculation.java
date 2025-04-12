package com.credit.model;

import java.util.List;

public class LoanCalculation {
    private String vehicleType;
    private String vehicleCondition;
    private double totalLoanAmt;
    private double downPaymentAmt;
    private int tenor;
    private List<LoanCalculationDetail> data;

    public LoanCalculation(String vehicleType, String vehicleCondition, double totalLoanAmt, double downPaymentAmt, int tenor, List<LoanCalculationDetail> data) {
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
        this.totalLoanAmt = totalLoanAmt;
        this.downPaymentAmt = downPaymentAmt;
        this.tenor = tenor;
        this.data = data;
    }

    public LoanCalculation(String sheetName, LoanCalculation loanCalculation) {
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public double getTotalLoanAmt() {
        return totalLoanAmt;
    }

    public void setTotalLoanAmt(double totalLoanAmt) {
        this.totalLoanAmt = totalLoanAmt;
    }

    public double getDownPaymentAmt() {
        return downPaymentAmt;
    }

    public void setDownPaymentAmt(double downPaymentAmt) {
        this.downPaymentAmt = downPaymentAmt;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public List<LoanCalculationDetail> getData() {
        return data;
    }

    public void setData(List<LoanCalculationDetail> data) {
        this.data = data;
    }
}
