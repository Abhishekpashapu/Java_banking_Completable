package com.abhi.bank;


import com.abhi.model.Loan;
import com.abhi.model.User;

import java.util.List;

public class BOBBank implements BankLoanProvider {

    @Override
    public List<Loan> getExistingLoans(User user) {

        System.out.println("Fetching HDFC loan details for "
                + user.getUserName());

        return List.of(
                new Loan("BOB",100000.00,40000.00,2.00,1300.00,24,12)

        );
    }
}
