package com.credit.view;

import com.credit.model.Loan;
import com.credit.model.LoanCalculation;
import com.credit.model.LoanHistory;

import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        System.out.println("Menu :");
        System.out.println("1. Hitung simulasi pinjaman");
        System.out.println("2. Lihat daftar riwayat perhitungan pinjaman");
        System.out.println("3. Keluar");
        System.out.print("Masukkan pilihan : ");
    }

    public Loan getInput(){
        System.out.println("\n===== Simulasi Perhitungan Pinjaman =====");
        System.out.print("Masukkan jenis kendaraan (Motor/Mobil)     : ");
        String vehicleType = scanner.nextLine();
        System.out.print("Masukkan kondisi kendaraan (Baru/Bekas)    : ");
        String vehicleCondition = scanner.nextLine();
        System.out.print("Masukkan tahun kendaraan (YYYY)            : ");
        int vehicleYear = scanner.nextInt();
        System.out.print("Masukkan total nominal pinjaman            : ");
        double totalLoanAmt = scanner.nextDouble();
        System.out.print("Masukkan jangka waktu pinjaman (1-6 tahun) : ");
        int tenor = scanner.nextInt();
        System.out.print("Masukkan jumlah uang muka                  : ");
        double downPaymentAmt = scanner.nextDouble();

        return new Loan(vehicleType, vehicleCondition, vehicleYear, totalLoanAmt, tenor, downPaymentAmt);
    }

    public Boolean getConfirmationSaveLoanCalculationHistory() {
        System.out.println("\nApakah anda ingin menyimpan riwayat simulasi perhitungan pinjaman ? ");
        System.out.print("Masukkan pilihan anda (1. Simpan / 2. Tidak) : ");
        int userConfirmation = scanner.nextInt();
        scanner.nextLine();

        return userConfirmation == 1 ? true : false;
    }

    public String getSheetLoanHistory() {
        System.out.print("Masukkan nama sheet untuk menyimpan data riwayat simulasi perhitungan pinjaman : ");
        String sheetName = scanner.nextLine();

        return sheetName;
    }

    public void calculateMonthlyInstallmentAndInterestRate(LoanCalculation loanCalculation) {
        System.out.println("===== Hasil Simulasi Perhitungan Cicilan =====");
        System.out.printf("Tipe Kendaraan    : %s\n", loanCalculation.getVehicleType());
        System.out.printf("Kondisi Kendaraan : %s\n", loanCalculation.getVehicleCondition());
        System.out.printf("Jumlah Pinjaman   : %.2f\n", loanCalculation.getTotalLoanAmt());
        System.out.printf("Jumlah Uang Muka  : %.2f\n", loanCalculation.getDownPaymentAmt());
        System.out.printf("Tenor pinjaman    : %s\n", loanCalculation.getTenor());

        loanCalculation.getData().forEach(data -> {
            System.out.printf("\nPembayaran cicilan tahun ke        : %d\n", data.getYear());
            System.out.printf("Bunga                              : %.1f\n", data.getInterestRate());
            System.out.printf("Sisa pinjaman pokok                : %.2f\n", data.getRemainingPrincipalLoanAmt());
            System.out.printf("Sisa pinjaman pokok seteleah bunga : %.2f\n", data.getRemainingPrincipalLoanAfterInterestAmt());
            System.out.printf("Pembayaran cicilan pertahun        : %.2f\n", data.getYearlyInstallmentAmt());
            System.out.printf("Pembayaran cicilan perbulan        : %.2f\n", data.getMonthlyInstallmentAmt());
        });

        System.out.println();
    }

    public void getListLoanCalulationHistoryData(Map<Integer, LoanHistory> loanHistoryMap) {
        System.out.println("===== Daftar Data Riwayat Simulasi Perhitungan Pinjaman =====");
        for (int key : loanHistoryMap.keySet()) {
            System.out.println(key + ". " + loanHistoryMap.get(key).getSheetName());
        }

        // pilih mana yang mau di show
        System.out.print("Masukkan nomor data riwayat simulasi perhitungan yang ingin anda lihat : ");
        int sheetNumber = scanner.nextInt();
        scanner.nextLine();

        if (loanHistoryMap.containsKey(sheetNumber)) {
            calculateMonthlyInstallmentAndInterestRate(loanHistoryMap.get(sheetNumber).getData());
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
