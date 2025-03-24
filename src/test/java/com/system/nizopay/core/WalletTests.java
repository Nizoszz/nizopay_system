package com.system.nizopay.core;

import com.system.nizopay.core.model.Wallet;
import com.system.nizopay.core.model.WalletStatus;
import com.system.nizopay.util.WalletFactory;
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
        wallet = WalletFactory.createValidWallet();
        walletWithCard = WalletFactory.createValidWalletWithValidCard();
    }
    @Test
    void shouldStartWithNoCreditRequested(){
        assertThat(wallet.getWalletStatus()).isEqualTo(WalletStatus.NOT_REQUESTED);
        assertThat(wallet.getCreditLimit()).isZero();
    }
    @Test
    void shouldSetCreditToPendingWhenRequestingCredit(){
        wallet.requestCredit();
        assertThat(wallet.getWalletStatus()).isEqualTo(WalletStatus.PENDING);
    }
    @Test
    void shouldApprovedCreditWhenInPendingStatus(){
        wallet.requestCredit();
        wallet.approveCredit(new BigDecimal("1600"));
        assertThat(wallet.getWalletStatus()).isEqualTo(WalletStatus.APPROVED);
        assertThat(wallet.getCreditLimit()).isEqualByComparingTo(new BigDecimal("1600"));
    }
    @Test
    void shouldRejectCreditWhenInPendingStatus(){
        wallet.requestCredit();
        wallet.rejectCredit();
        assertThat(wallet.getWalletStatus()).isEqualTo(WalletStatus.REJECTED);
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