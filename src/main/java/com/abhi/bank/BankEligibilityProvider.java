package com.abhi.bank;

import com.abhi.model.EligibilityResult;
import com.abhi.model.User;

public interface BankEligibilityProvider {
    EligibilityResult checkEligibility(User user);

}
