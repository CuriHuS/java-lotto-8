package lotto.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    @DisplayName("1등 상금은 20억원이다")
    void firstPrize() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
    }

    @Test
    @DisplayName("2등 상금은 3000만원이다")
    void secondPrize() {
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
    }

    @Test
    @DisplayName("3등 상금은 150만원이다")
    void thirdPrize() {
        assertThat(Rank.THIRD.getPrize()).isEqualTo(1_500_000);
    }

    @Test
    @DisplayName("4등 상금은 5만원이다")
    void fourthPrize() {
        assertThat(Rank.FOURTH.getPrize()).isEqualTo(50_000);
    }

    @Test
    @DisplayName("5등 상금은 5천원이다")
    void fifthPrize() {
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000);
    }

    @Test
    @DisplayName("낙첨 상금은 0원이다")
    void noRankPrize() {
        assertThat(Rank.NO_RANK.getPrize()).isEqualTo(0);
    }

    @Test
    @DisplayName("모든 Rank를 조회할 수 있다")
    void values() {
        Rank[] ranks = Rank.values();

        assertThat(ranks).hasSize(6);
        assertThat(ranks).containsExactly(
                Rank.FIRST, Rank.SECOND, Rank.THIRD,
                Rank.FOURTH, Rank.FIFTH, Rank.NO_RANK
        );
    }
}