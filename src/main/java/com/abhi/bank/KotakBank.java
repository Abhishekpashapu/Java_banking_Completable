package com.abhi.bank;

import com.abhi.model.EligibilityResult;
import com.abhi.model.Loan;
import com.abhi.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KotakBank implements BankEligibilityProvider {

    private static final double SALARY_HOLD_PERCENTAGE = 0.40;
    private static final double DEFAULT_ANNUAL_INTEREST_RATE = 10.5;
    private static final int DEFAULT_TENURE_MONTHS = 60;

    private final List<BankLoanProvider> banks;
    private final double annualInterestRate;
    private final int tenureMonths;

    public KotakBank(List<BankLoanProvider> banks) {
        this(banks, DEFAULT_ANNUAL_INTEREST_RATE, DEFAULT_TENURE_MONTHS);
    }

    public KotakBank(List<BankLoanProvider> banks, double annualInterestRate, int tenureMonths) {
        this.banks = banks;
        this.annualInterestRate = annualInterestRate;
        this.tenureMonths = tenureMonths;
    }

    @Override
    public EligibilityResult checkEligibility(User user) {
        ExecutorService executor = Executors.newFixedThreadPool(banks.size());
        List<String> threadStatuses = Collections.synchronizedList(new ArrayList<>());

        try {
            List<CompletableFuture<List<Loan>>> futures = banks.stream()
                    .map(bank -> CompletableFuture.supplyAsync(() -> {
                        String bankName = bank.getClass().getSimpleName();
                        String threadName = Thread.currentThread().getName();
                        long start = System.currentTimeMillis();

                        System.out.println("[" + threadName + "] RUNNING -> Fetching loans from " + bankName);

                        try {
                            List<Loan> loans = bank.getExistingLoans(user);
                            long elapsed = System.currentTimeMillis() - start;
                            String status = String.format(
                                    "%s | Thread: %s | Status: COMPLETED | Loans Found: %d | Time: %d ms",
                                    bankName, threadName, loans.size(), elapsed
                            );
                            threadStatuses.add(status);
                            return loans;
                        } catch (Exception ex) {
                            long elapsed = System.currentTimeMillis() - start;
                            String status = String.format(
                                    "%s | Thread: %s | Status: FAILED (%s) | Time: %d ms",
                                    bankName, threadName, ex.getMessage(), elapsed
                            );
                            threadStatuses.add(status);
                            return Collections.<Loan>emptyList();
                        }
                    }, executor))
                    .toList();

            // Wait for all bank tasks to complete
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            List<Loan> allLoans = futures.stream()
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .toList();

            double totalEmi = allLoans.stream()
                    .mapToDouble(Loan::getMonthlyEmi)
                    .sum();

            double salary = user.getSalary();
            double heldSalary = salary * SALARY_HOLD_PERCENTAGE;
            double maxAllowedEmi = salary - heldSalary;
            double availableEmiCapacity = maxAllowedEmi - totalEmi;

            boolean eligible = availableEmiCapacity > 0;
            double maxEligibleLoanAmount = eligible
                    ? calculateMaxLoanAmount(availableEmiCapacity, annualInterestRate, tenureMonths)
                    : 0.0;

            String message = eligible
                    ? String.format("Eligible for a loan up to %.2f at %.1f%% for %d months",
                    maxEligibleLoanAmount, annualInterestRate, tenureMonths)
                    : "Not eligible: Existing EMI exceeds usable salary after 40% hold";

            return new EligibilityResult(
                    eligible,
                    heldSalary,
                    totalEmi,
                    Math.max(0.0, availableEmiCapacity),
                    maxEligibleLoanAmount,
                    message,
                    threadStatuses
            );
        } finally {
            executor.shutdown();
        }
    }

    private double calculateMaxLoanAmount(double monthlyEmi, double annualRate, int months) {
        double monthlyRate = annualRate / (12 * 100);
        if (monthlyRate == 0) {
            return monthlyEmi * months;
        }
        double factor = Math.pow(1 + monthlyRate, months);
        return monthlyEmi * (factor - 1) / (monthlyRate * factor);
    }
}