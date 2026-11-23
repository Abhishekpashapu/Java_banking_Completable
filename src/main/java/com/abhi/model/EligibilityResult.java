package com.abhi.model;

import java.util.List;

public class EligibilityResult {

    private boolean eligible;
    private double heldSalary;
    private double totalExistingEmi;
    private double availableEmiCapacity;
    private double maxEligibleLoanAmount;
    private String message;
    private List<String> threadStatuses;

    public EligibilityResult(
            boolean eligible,
            double heldSalary,
            double totalExistingEmi,
            double availableEmiCapacity,
            double maxEligibleLoanAmount,
            String message,
            List<String> threadStatuses) {
        this.eligible = eligible;
        this.heldSalary = heldSalary;
        this.totalExistingEmi = totalExistingEmi;
        this.availableEmiCapacity = availableEmiCapacity;
        this.maxEligibleLoanAmount = maxEligibleLoanAmount;
        this.message = message;
        this.threadStatuses = threadStatuses;
    }

    public boolean isEligible() {
        return eligible;
    }

    public double getHeldSalary() {
        return heldSalary;
    }

    public double getTotalExistingEmi() {
        return totalExistingEmi;
    }

    public double getAvailableEmiCapacity() {
        return availableEmiCapacity;
    }

    public double getMaxEligibleLoanAmount() {
        return maxEligibleLoanAmount;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getThreadStatuses() {
        return threadStatuses;
    }
}