package com.credit.controller;
import com.credit.service.LoanService;

public class LoanController {
    private LoanService loanService;

    public LoanController() {
        this.loanService = new LoanService();
    }

    public void start(String[] args) {
        loanService.executeLoanSimulation(args);
    }
}
