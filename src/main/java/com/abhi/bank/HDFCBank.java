package com.abhi.bank;

import com.abhi.model.Loan;
import com.abhi.model.User;

import java.util.List;

public class HDFCBank implements BankLoanProvider {

    @Override
    public List<Loan> getExistingLoans(User user) {

        System.out.println("Fetching HDFC loan details for "
                + user.getUserName());

        return List.of(
                new Loan("HDFC",100000.00,80000.00,2.00,1100.00,36,36)
        );
    }
}
