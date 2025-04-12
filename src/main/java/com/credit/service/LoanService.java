package com.credit.service;

import com.credit.helper.Util;
import com.credit.model.Loan;
import com.credit.model.LoanCalculation;
import com.credit.model.LoanCalculationDetail;
import com.credit.model.LoanHistory;
import com.credit.view.ConsoleView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.credit.helper.Constants.*;

public class LoanService {
    private Scanner scanner;
    private ConsoleView view;
    private Util util;
    private Map<Integer, LoanHistory> loanHistoryMap;
    private int sheetNumber;

    public LoanService() {
        this.scanner = new Scanner(System.in);
        this.view = new ConsoleView();
        this.util = new Util();
        this.loanHistoryMap = new HashMap<>();
        this.sheetNumber = 1;
    }

    public void executeLoanSimulation(String[] args) {
        if (args.length > 0) {
            String inputFilePath = args[0];

            try {
                File inputFile = new File(inputFilePath);
                scanner = new Scanner(inputFile);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    if (parts.length == 6) {
                        String vehicleType = parts[0].trim();
                        String condition = parts[1].trim();
                        int year = Integer.parseInt(parts[2].trim());
                        double totalLoan = Double.parseDouble(parts[3].trim());
                        int tenor = Integer.parseInt(parts[4].trim());
                        double downPayment = Double.parseDouble(parts[5].trim());

                        Loan loan = new Loan(vehicleType, condition, year, totalLoan, tenor, downPayment);
                        calculateMonthlyInstallmentAndInterestRate(loan);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error saat memproses file: " + e.getMessage());
            }
        }else {
            while (true) {
                view.displayMenu();

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        calculateLoan();
                        break;
                    case 2:
                        getListLoanCalulationHistoryData(loanHistoryMap);
                        break;
                    case 3:
                        System.out.println("Terima kasih telah menggunkan aplikasi kami :D");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silahkan coba kembali.");
                }
            }
        }

        view.closeScanner();
    }

    private void calculateLoan() {
        Loan loan = view.getInput();
        calculateInstallment(loan);
    }

    public void calculateInstallment(Loan loan) {
        if (isValidInput(loan)) {
            LoanCalculation loanCalculation = calculateMonthlyInstallmentAndInterestRate(loan);

            Boolean isSaved = view.getConfirmationSaveLoanCalculationHistory();
            if (isSaved){
                String sheetName = view.getSheetLoanHistory();
                saveLoanCalculationHistory(loanCalculation, sheetName);
            }
        }
    }

    public LoanCalculation calculateMonthlyInstallmentAndInterestRate(Loan loan) {
        List<LoanCalculationDetail> loanCalculationDetailList = new ArrayList<>();

        double baseInterestRate = calculateBaseInterestRate(loan.getVehicleType());
        double finalInterestRate = baseInterestRate;
        double remainingPrincipalLoan = (loan.getTotalLoanAmt() - loan.getDownPaymentAmt());
        double remainingPrincipalLoanAfterInterest;
        double yearlyInstallment;
        double monthlyInstallment;

        for (int i = 0; i < loan.getTenor(); i++) {
            finalInterestRate = calculateInterestRate(i, finalInterestRate);
            remainingPrincipalLoanAfterInterest = calculateRemainingPrincipalLoanAfterInterest(remainingPrincipalLoan, finalInterestRate);
            monthlyInstallment = calculateMonthlyInstallment(remainingPrincipalLoanAfterInterest, loan.getTenor(), i);
            yearlyInstallment = calculateYearlyInstallment(monthlyInstallment);

            loanCalculationDetailList.add(new LoanCalculationDetail(i+1, finalInterestRate, remainingPrincipalLoan, remainingPrincipalLoanAfterInterest, yearlyInstallment, monthlyInstallment));

            remainingPrincipalLoan = remainingPrincipalLoanAfterInterest - yearlyInstallment;
        }

        LoanCalculation loanCalculation = new LoanCalculation(loan.getVehicleType(), loan.getVehicleCondition(), loan.getTotalLoanAmt(), loan.getDownPaymentAmt(), loan.getTenor(), loanCalculationDetailList);
        view.calculateMonthlyInstallmentAndInterestRate(loanCalculation);

        return loanCalculation;
    }

    private double calculateBaseInterestRate(String vehicleType) {
        return vehicleType.equalsIgnoreCase(VEHICLE_TYPE_CAR) ? 8.0 : 9.0;
    }

    private double calculateInterestRate(int year, double finalInterestRate) {
        if (year > 0) {
            finalInterestRate += (year % 2 == 0) ? 0.5 : 0.1;
        }
        return finalInterestRate;

    }

    private double calculateRemainingPrincipalLoanAfterInterest(double remainingPrincipalLoan, double finalInterestRate) {
        return (remainingPrincipalLoan * finalInterestRate/100) + remainingPrincipalLoan;
    }

    private double calculateMonthlyInstallment(double remainingPrincipalLoanAfterInterest, int tenor, int year) {
        int remainingMonths = (12 * tenor) - (12 * year);
        return remainingPrincipalLoanAfterInterest / remainingMonths;
    }

    private double calculateYearlyInstallment(double monthlyInstallment) {
        return monthlyInstallment * 12;
    }

    private boolean isValidInput(Loan loan) {
        if (!util.isValidYear(loan.getVehicleYear())){
            System.out.println("Tahun kendaraan baru tidak boleh lebih dari 4 digit.");
            return false;
        }

        if (!util.isValidTotalLoan(loan.getTotalLoanAmt())){
            System.out.println("Total pinjaman tidak boleh lebih dari 1M.");
        }

        if (!util.isValidYaerNewVehicle(loan.getVehicleCondition(), loan.getVehicleYear())) {
            System.out.println("Tahun kendaraan baru tidak boleh kurang dari tahun ini.");
            return false;
        }

        if (!util.isValidTenor(loan.getTenor())) {
            System.out.println("Tenor pinjaman harus antara 1 hingga 6 tahun.");
            return false;
        }

        if (!util.isValidDownPayment(loan)){
            System.out.printf("DP untuk kendaraan %s minimal %.0f%% dari pinjaman.%n", loan.getVehicleCondition(), util.getRequiredDownPaymentPercentage(loan.getVehicleCondition()) * 100);
            return false;
        }

        return true;
    }

    private void saveLoanCalculationHistory(LoanCalculation loanCalculation, String sheetName) {
        loanHistoryMap.put(sheetNumber, new LoanHistory(sheetName, loanCalculation));
        sheetNumber++;
        System.out.println("Data riwayat simulasi perhitungan pinjaman telah tersimpan.\n");
    }

    private void getListLoanCalulationHistoryData(Map<Integer, LoanHistory> loanHistoryMap) {
        if(!loanHistoryMap.isEmpty()){
            view.getListLoanCalulationHistoryData(loanHistoryMap);
        }
    }
}
