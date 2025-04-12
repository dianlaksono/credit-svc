package com.credit;

import com.credit.controller.LoanController;

public class Application {
    public static void main(String[] args) {
        LoanController controller = new LoanController();
        controller.start(args);
    }
}