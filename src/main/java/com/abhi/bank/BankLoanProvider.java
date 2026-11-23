package com.abhi.bank;

import com.abhi.model.Loan;
import com.abhi.model.User;
import java.util.List;

public interface BankLoanProvider {
    List<Loan> getExistingLoans(User user);
}