package com.credit.helper;

import com.credit.model.Loan;

import java.time.Year;

import static com.credit.helper.Constants.*;

public class Util {
    public boolean isValidYear(int year) {
        return year >= 0 && year <= 9999;
    }

    public boolean isValidTotalLoan(double totalLoanAmt) {
        return totalLoanAmt > 0 && totalLoanAmt <= 1_000_000_000;
    }

    public boolean isValidYaerNewVehicle(String vehicleCondition, int vehicleYear) {
        int currentYear = Year.now().getValue();
        if (vehicleCondition.equalsIgnoreCase(VEHICLE_CONDITION_NEW)) {
            return vehicleYear >= (currentYear - 1);
        }
        return true;
    }

    public boolean isValidTenor(int tenor) {
        return tenor >= 1 && tenor <= 6;
    }

    public boolean isValidDownPayment(Loan loan) {
        double requiredDownPaymentPercentage = getRequiredDownPaymentPercentage(loan.getVehicleCondition());
        double requiredDownPayment = requiredDownPaymentPercentage * loan.getTotalLoanAmt();
        if (loan.getDownPaymentAmt() < requiredDownPayment) {
            return false;
        }
        return true;
    }

    public double getRequiredDownPaymentPercentage(String vehicleCondition) {
        double requiredDownPaymentPercentage = 0.0;
        if (vehicleCondition.equalsIgnoreCase(VEHICLE_CONDITION_NEW)) {
            requiredDownPaymentPercentage =  0.35;
        } else if (vehicleCondition.equalsIgnoreCase(VEHICLE_CONDITION_SECOND)) {
            requiredDownPaymentPercentage = 0.25;
        }
        return requiredDownPaymentPercentage;
    }
}
