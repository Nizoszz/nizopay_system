package com.system.nizopay.domain;

import com.system.nizopay.util.WalletCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class WalletTests{
    private Wallet wallet;
    private Wallet walletWithCard;
    @BeforeEach
    void setUp() {
        wallet = WalletCreator.createValidWallet();
        walletWithCard = WalletCreator.createValidWalletWithValidCard();
    }
    @Test
    void shouldStartWithNoCreditRequested(){
        assertThat(wallet.getCreditStatus()).isEqualTo(CreditStatus.NOT_REQUESTED);
        assertThat(wallet.getCreditLimit()).isZero();
    }
    @Test
    void shouldSetCreditToPendingWhenRequestingCredit(){
        wallet.requestCredit();
        assertThat(wallet.getCreditStatus()).isEqualTo(CreditStatus.PENDING);
    }
    @Test
    void shouldApprovedCreditWhenInPendingStatus(){
        wallet.requestCredit();
        wallet.approveCredit(new BigDecimal("1600"));
        assertThat(wallet.getCreditStatus()).isEqualTo(CreditStatus.APPROVED);
        assertThat(wallet.getCreditLimit()).isEqualByComparingTo(new BigDecimal("1600"));
    }
    @Test
    void shouldRejectCreditWhenInPendingStatus(){
        wallet.requestCredit();
        wallet.rejectCredit();
        assertThat(wallet.getCreditStatus()).isEqualTo(CreditStatus.REJECTED);
    }
    @Test
    void shouldNotApprovedCreditIfNotPending(){
        assertThatThrownBy(() -> wallet.approveCredit(new BigDecimal("1600")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit can only be approved when in PENDING status.");
    }

    @Test
    void shouldNotRejectCreditIfNotPending(){
        assertThatThrownBy(() -> wallet.rejectCredit())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit can only be rejected when in PENDING status.");
    }
    @Test
    void shouldNotApprovedNegativeCreditLimit(){
        assertThatThrownBy(() -> {
            wallet.requestCredit();
            wallet.approveCredit(new BigDecimal("-1600"));
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit limit must be a positive value.");
    }

    @Test
    void shouldAddNewCreditCard(){
        assertThat(walletWithCard.getCards()).hasSize(1);
    }
    @Test
    void shouldRemoveCreditCard(){
        walletWithCard.removeCard(walletWithCard.getCards().get(0));
        assertThat(walletWithCard.getCards()).isEmpty();
    }
}