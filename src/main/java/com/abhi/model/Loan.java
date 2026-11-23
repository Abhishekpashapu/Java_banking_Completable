
package com.abhi.model;

public class Loan {

    private String bankName;
    private Double originalLoanAmount;
    private Double remainingAmount;
    private Double intrestRate;
    private Double monthlyEmi;
    private int originalTenureAmount;
    private int remainingTenureAmount;

    public Loan(String bankName , Double originalLoanAmount,Double remainingAmount , Double intrestRate , Double monthlyEmi, int originalTenureAmount,int remainingTenureAmount) {
        this.remainingAmount = remainingAmount;
        this.bankName = bankName;
        this.intrestRate = intrestRate;
        this.monthlyEmi = monthlyEmi;
        this.originalLoanAmount = originalLoanAmount;
        this.originalTenureAmount = originalTenureAmount;
        this.remainingTenureAmount = remainingTenureAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public Double getOutStandingAmount() {
        return remainingAmount;
    }

    public Double getOriginalLoanAmount() {
        return originalLoanAmount;
    }

    public int getOriginalTenureAmount() {
        return originalTenureAmount;
    }

    public int getRemainingTenureAmount() {
        return remainingTenureAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public Double getIntrestRate() {
        return intrestRate;
    }

    public Double getMonthlyEmi() {
        return monthlyEmi;
    }
}
