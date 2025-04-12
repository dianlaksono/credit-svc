package com.credit.model;


public class Loan {
    private String vehicleType;
    private String vehicleCondition;
    private int vehicleYear;
    private double totalLoanAmt;
    private int tenor;
    private double downPaymentAmt;

    public Loan(String vehicleType, String vehicleCondition, int vehicleYear, double totalLoanAmt, int tenor, double downPaymentAmt) {
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
        this.vehicleYear = vehicleYear;
        this.totalLoanAmt = totalLoanAmt;
        this.tenor = tenor;
        this.downPaymentAmt = downPaymentAmt;
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

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public double getTotalLoanAmt() {
        return totalLoanAmt;
    }

    public void setTotalLoanAmt(double totalLoanAmt) {
        this.totalLoanAmt = totalLoanAmt;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public double getDownPaymentAmt() {
        return downPaymentAmt;
    }

    public void setDownPaymentAmt(double downPaymentAmt) {
        this.downPaymentAmt = downPaymentAmt;
    }
}
