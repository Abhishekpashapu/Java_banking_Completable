package com.abhi.bank;


import com.abhi.model.Loan;
import com.abhi.model.User;

import java.util.List;

public class IciciBank implements BankLoanProvider {

    @Override
    public List<Loan> getExistingLoans(User user) {

        System.out.println("Fetching HDFC loan details for "
                + user.getUserName());

        return List.of(
                new Loan("icici",100000.00,50000.00,2.00,1200.00,48,32)
        );
    }
}
