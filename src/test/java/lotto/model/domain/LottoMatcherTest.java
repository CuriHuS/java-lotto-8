package lotto.model.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatcherTest {
    private LottoMatcher lottoMatcher;
    private Lotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottoMatcher = new LottoMatcher(winningLotto, 7);
    }

    @Test
    @DisplayName("6개 번호가 모두 일치하면 1등이다")
    void matchSixNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 번호 일치하고 보너스 번호가 일치하면 2등이다")
    void matchFiveNumbersWithBonus() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개 번호만 일치하면 3등이다")
    void matchFiveNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개 번호가 일치하면 4등이다")
    void matchFourNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 10, 11));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 번호가 일치하면 5등이다")
    void matchThreeNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하로 일치하면 낙첨이다")
    void matchLessThanThreeNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 10, 11, 12, 13));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.NO_RANK);
    }

    @Test
    @DisplayName("일치하는 번호가 하나도 없으면 낙첨이다")
    void matchNoNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.NO_RANK);
    }

    @ParameterizedTest
    @DisplayName("일치하는 번호 개수를 정확히 카운트한다")
    @CsvSource({
            "1,2,3,4,5,6, 6",
            "1,2,3,4,5,10, 5",
            "1,2,3,4,10,11, 4",
            "1,2,3,10,11,12, 3",
            "1,2,10,11,12,13, 2",
            "1,10,11,12,13,14, 1",
            "10,11,12,13,14,15, 0"
    })
    void countMatchingNumbers(int n1, int n2, int n3, int n4, int n5, int n6, int expected) {
        Lotto lotto = new Lotto(Arrays.asList(n1, n2, n3, n4, n5, n6));
        int matchCount = lottoMatcher.countMatchingNumbers(lotto);
        assertThat(matchCount).isEqualTo(expected);
    }

    @Test
    @DisplayName("보너스 번호가 포함되어 있으면 true를 반환한다")
    void hasBonusNumber_true() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        boolean hasBonus = lottoMatcher.hasBonusNumber(lotto);
        assertThat(hasBonus).isTrue();
    }

    @Test
    @DisplayName("보너스 번호가 포함되어 있지 않으면 false를 반환한다")
    void hasBonusNumber_false() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10));
        boolean hasBonus = lottoMatcher.hasBonusNumber(lotto);
        assertThat(hasBonus).isFalse();
    }

    @Test
    @DisplayName("4개 일치하고 보너스 번호가 있어도 4등이다")
    void matchFourNumbersWithBonus() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 7, 10));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치하고 보너스 번호가 있어도 5등이다")
    void matchThreeNumbersWithBonus() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 7, 10, 11));
        Rank rank = lottoMatcher.determineRank(lotto);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }
}