package lotto.model.service;

import lotto.model.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    @DisplayName("구매 금액에 따라 로또를 발행한다")
    void issueLottos() {
        int purchaseAmount = 5000;
        Lottos lottos = lottoService.issueLottos(purchaseAmount);

        assertThat(lottos.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("구매 금액이 1000원 미만이면 예외가 발생한다")
    void issueLottos_underMinimum() {
        assertThatThrownBy(() -> lottoService.issueLottos(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구매 금액은 1,000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아니면 예외가 발생한다")
    void issueLottos_notMultipleOf1000() {
        assertThatThrownBy(() -> lottoService.issueLottos(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구매 금액은 1,000원 단위로 떨어져야 합니다");
    }

    @Test
    @DisplayName("당첨 결과를 확인한다")
    void checkWinning() {
        Lottos lottos = lottoService.issueLottos(3000);
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        LottoResults results = lottoService.checkWinning(lottos, winningLotto, bonusNumber);

        assertThat(results).isNotNull();
        assertThat(results.getResults()).hasSize(3);
    }

    @Test
    @DisplayName("수익률을 계산한다")
    void calculateReturnRate() {
        LottoResults results = new LottoResults();
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));
        results.addResult(lotto1, Rank.FIFTH);  // 5000원
        results.addResult(lotto2, Rank.NO_RANK);  // 0원

        int purchaseAmount = 2000;
        double returnRate = lottoService.calculateReturnRate(results, purchaseAmount);

        assertThat(returnRate).isEqualTo(2.5);  // 5000 / 2000 = 2.5
    }

    @Test
    @DisplayName("수익률이 소수점 둘째 자리에서 반올림된다")
    void calculateReturnRate_rounded() {
        LottoResults results = new LottoResults();
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        results.addResult(lotto, Rank.FIFTH);  // 5000원

        int purchaseAmount = 3000;
        double returnRate = lottoService.calculateReturnRate(results, purchaseAmount);

        assertThat(returnRate).isEqualTo(1.67);  // 5000 / 3000 = 1.666... -> 1.67
    }

    @Test
    @DisplayName("당첨되지 않으면 수익률이 0이다")
    void calculateReturnRate_noWin() {
        LottoResults results = new LottoResults();
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        results.addResult(lotto, Rank.NO_RANK);

        int purchaseAmount = 1000;
        double returnRate = lottoService.calculateReturnRate(results, purchaseAmount);

        assertThat(returnRate).isEqualTo(0.0);
    }
}