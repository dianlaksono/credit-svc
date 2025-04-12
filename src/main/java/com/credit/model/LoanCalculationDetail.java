package com.credit.model;

public class LoanCalculationDetail {
    private int year;
    private double interestRate;
    private double remainingPrincipalLoanAmt;
    private double remainingPrincipalLoanAfterInterestAmt;
    private double yearlyInstallmentAmt;
    private double monthlyInstallmentAmt;

    public LoanCalculationDetail(int year, double interestRate, double remainingPrincipalLoanAmt, double remainingPrincipalLoanAfterInterestAmt, double yearlyInstallmentAmt, double monthlyInstallmentAmt) {
        this.year = year;
        this.interestRate = interestRate;
        this.remainingPrincipalLoanAmt = remainingPrincipalLoanAmt;
        this.remainingPrincipalLoanAfterInterestAmt = remainingPrincipalLoanAfterInterestAmt;
        this.yearlyInstallmentAmt = yearlyInstallmentAmt;
        this.monthlyInstallmentAmt = monthlyInstallmentAmt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getRemainingPrincipalLoanAmt() {
        return remainingPrincipalLoanAmt;
    }

    public void setRemainingPrincipalLoanAmt(double remainingPrincipalLoanAmt) {
        this.remainingPrincipalLoanAmt = remainingPrincipalLoanAmt;
    }

    public double getRemainingPrincipalLoanAfterInterestAmt() {
        return remainingPrincipalLoanAfterInterestAmt;
    }

    public void setRemainingPrincipalLoanAfterInterestAmt(double remainingPrincipalLoanAfterInterestAmt) {
        this.remainingPrincipalLoanAfterInterestAmt = remainingPrincipalLoanAfterInterestAmt;
    }

    public double getYearlyInstallmentAmt() {
        return yearlyInstallmentAmt;
    }

    public void setYearlyInstallmentAmt(double yearlyInstallmentAmt) {
        this.yearlyInstallmentAmt = yearlyInstallmentAmt;
    }

    public double getMonthlyInstallmentAmt() {
        return monthlyInstallmentAmt;
    }

    public void setMonthlyInstallmentAmt(double monthlyInstallmentAmt) {
        this.monthlyInstallmentAmt = monthlyInstallmentAmt;
    }
}
