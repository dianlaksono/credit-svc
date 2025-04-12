import com.credit.helper.Util;
import com.credit.model.Loan;
import com.credit.service.LoanService;

public class LoanSimulationTest {
    private LoanService loanService;
    private Util util;

    public LoanSimulationTest() {
        this.loanService = new LoanService();
        this.util = new Util();
    }


    public static void main(String[] args) {
        LoanSimulationTest test = new LoanSimulationTest();
        test.testExecuteLoanSimulationManual();
        test.isValidYearValidation();
        test.isValidTotalLoanValidation();
        test.isValidYaerNewVehicleValidation();
        test.isValidTenorValidation();
        test.isValidDownPaymentValidation();
    }

    private void testExecuteLoanSimulationManual() {
        Loan loan = new Loan("Mobil", "Bekas", 2020, 100000000, 3, 25000000);
        loanService.calculateMonthlyInstallmentAndInterestRate(loan);
    }

    private void isValidYearValidation() {
        System.out.println("Testing isValidYearValidation:");
        System.out.println("Year 2020: " + util.isValidYear(2020)); // Expected: true
        System.out.println("Year -1: " + util.isValidYear(-1)); // Expected: false
        System.out.println("Year 10000: " + util.isValidYear(10000)); // Expected: false
        System.out.println("Year 9999: " + util.isValidYear(9999)); // Expected: true
    }

    private void isValidTotalLoanValidation() {
        System.out.println("Testing isValidTotalLoan:");
        System.out.println("Total Loan 50000000: " + util.isValidTotalLoan(50000000)); // Expected: true
        System.out.println("Total Loan 0: " + util.isValidTotalLoan(0)); // Expected: false
        System.out.println("Total Loan 1000000000: " + util.isValidTotalLoan(1000000000)); // Expected: true
        System.out.println("Total Loan 999999999: " + util.isValidTotalLoan(1000000001)); // Expected: false
    }

    private void isValidYaerNewVehicleValidation() {
        System.out.println("Testing isValidYearNewVehicle:");
        System.out.println("New Vehicle, Year 2022: " + util.isValidYaerNewVehicle("Baru", 2024)); // Expected: true
        System.out.println("New Vehicle, Year 2021: " + util.isValidYaerNewVehicle("Baru", 2021)); // Expected: false
        System.out.println("New Vehicle, Year 2020: " + util.isValidYaerNewVehicle("Baru", 2025)); // Expected: true
        System.out.println("Second Vehicle, Year 2020: " + util.isValidYaerNewVehicle("Bekas", 2020)); // Expected: true
    }

    private void isValidTenorValidation() {
        System.out.println("Testing isValidTenor:");
        System.out.println("Tenor 3: " + util.isValidTenor(3)); // Expected: true
        System.out.println("Tenor 0: " + util.isValidTenor(0)); // Expected: false
        System.out.println("Tenor 7: " + util.isValidTenor(7)); // Expected: false
    }

    private void isValidDownPaymentValidation() {
        System.out.println("Testing isValidDownPayment For New Vehicle:");
        Loan loan1 = new Loan("Car", "Baru", 2022, 50000000, 5, 20000000); // 35% of 50000000 = 17500000
        System.out.println("Valid Down Payment: " + util.isValidDownPayment(loan1)); // Expected: true
        Loan loan2 = new Loan("Car", "Baru", 2022, 50000000, 5, 10000000); // 35% of 50000000 = 17500000
        System.out.println("Invalid Down Payment: " + util.isValidDownPayment(loan2)); // Expected: false

        System.out.println("Testing isValidDownPayment For Second Vehicle:");
        Loan loan3 = new Loan("Car", "Bekas", 2022, 50000000, 5, 15000000); // 25% of 50000000 = 12500000
        System.out.println("Valid Down Payment: " + util.isValidDownPayment(loan1)); // Expected: true
        Loan loan4 = new Loan("Car", "Bekas", 2022, 50000000, 5, 10000000); // 25% of 50000000 = 17500000
        System.out.println("Invalid Down Payment: " + util.isValidDownPayment(loan2)); // Expected: false
    }
}
