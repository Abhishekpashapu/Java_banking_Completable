package com.abhi.service;

import com.abhi.bank.BOBBank;
import com.abhi.bank.BankEligibilityProvider;
import com.abhi.bank.BankLoanProvider;
import com.abhi.bank.HDFCBank;
import com.abhi.bank.IciciBank;
import com.abhi.bank.KotakBank;
import com.abhi.model.EligibilityResult;
import com.abhi.model.User;
import java.util.List;
import java.util.Scanner;

public class LoanEligibilityService {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Kotak Annual Interest Rate (%): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter Loan Tenure (in months): ");
        int tenureMonths = scanner.nextInt();

        User user = new User("Abhi", "ABCDE1234F", 50000.0, 760);

        List<BankLoanProvider> banks = List.of(
                new HDFCBank(),
                new IciciBank(),
                new BOBBank()
        );

        BankEligibilityProvider kotak = new KotakBank(banks, interestRate, tenureMonths);
        EligibilityResult result = kotak.checkEligibility(user);

        System.out.println("\n------ THREAD EXECUTION STATUS ------");
        result.getThreadStatuses().forEach(System.out::println);

        System.out.println("\n------ KOTAK ELIGIBILITY RESULT ------");
        System.out.println("User: " + user.getUserName());
        System.out.println("PAN: " + user.getPanCard());
        System.out.println("CIBIL Score: " + user.getCibilScore());
        System.out.printf("Monthly Salary: %.2f%n", user.getSalary());
        System.out.printf("Held Salary (40%%): %.2f%n", result.getHeldSalary());
        System.out.printf("Total Existing EMI: %.2f%n", result.getTotalExistingEmi());
        System.out.printf("Available EMI Capacity: %.2f%n", result.getAvailableEmiCapacity());
        System.out.printf("Max Eligible Loan Amount: %.2f%n", result.getMaxEligibleLoanAmount());
        System.out.println("Eligible: " + result.isEligible());
        System.out.println("Message: " + result.getMessage());

        scanner.close();
    }
}