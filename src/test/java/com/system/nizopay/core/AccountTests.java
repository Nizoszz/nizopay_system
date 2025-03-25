package com.system.nizopay.core;

import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.model.AccountStatus;
import com.system.nizopay.util.AccountFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class AccountTests{
    private Account account;
    private Account accountWithCard;
    @BeforeEach
    void setUp() {
        account = AccountFactory.createValidAccount();
        accountWithCard = AccountFactory.createValidAccountWithValidCard();
    }
    @Test
    void shouldStartWithNoCreditRequested(){
        assertThat(account.getAccountStatus()).isEqualTo(AccountStatus.NOT_REQUESTED);
        assertThat(account.getCreditLimit()).isZero();
    }
    @Test
    void shouldSetCreditToPendingWhenRequestingCredit(){
        account.requestCredit();
        assertThat(account.getAccountStatus()).isEqualTo(AccountStatus.PENDING);
    }
    @Test
    void shouldApprovedCreditWhenInPendingStatus(){
        account.requestCredit();
        account.approveCredit(new BigDecimal("1600"));
        assertThat(account.getAccountStatus()).isEqualTo(AccountStatus.APPROVED);
        assertThat(account.getCreditLimit()).isEqualByComparingTo(new BigDecimal("1600"));
    }
    @Test
    void shouldRejectCreditWhenInPendingStatus(){
        account.requestCredit();
        account.rejectCredit();
        assertThat(account.getAccountStatus()).isEqualTo(AccountStatus.REJECTED);
    }
    @Test
    void shouldNotApprovedCreditIfNotPending(){
        assertThatThrownBy(() -> account.approveCredit(new BigDecimal("1600")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit can only be approved when in PENDING status.");
    }

    @Test
    void shouldNotRejectCreditIfNotPending(){
        assertThatThrownBy(() -> account.rejectCredit())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit can only be rejected when in PENDING status.");
    }
    @Test
    void shouldNotApprovedNegativeCreditLimit(){
        assertThatThrownBy(() -> {
            account.requestCredit();
            account.approveCredit(new BigDecimal("-1600"));
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit limit must be a positive value.");
    }

    @Test
    void shouldAddNewCreditCard(){
        assertThat(accountWithCard.getCards()).hasSize(1);
    }
    @Test
    void shouldRemoveCreditCard(){
        accountWithCard.removeCard(accountWithCard.getCards().get(0));
        assertThat(accountWithCard.getCards()).isEmpty();
    }
}