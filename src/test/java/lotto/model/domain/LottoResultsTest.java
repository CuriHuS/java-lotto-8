package lotto.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {
    @Test
    @DisplayName("로또 결과를 추가한다")
    void addResult() {
        LottoResults results = new LottoResults();
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        results.addResult(lotto, Rank.FIRST);
        assertThat(results.getResults()).containsKey(lotto);
        assertThat(results.getResults().get(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("등수별 당첨 개수를 집계한다")
    void getPrizeResults() {
        LottoResults results = new LottoResults();
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(Arrays.asList(13, 14, 15, 16, 17, 18));

        results.addResult(lotto1, Rank.FIRST);
        results.addResult(lotto2, Rank.THIRD);
        results.addResult(lotto3, Rank.THIRD);

        HashMap<Rank, Integer> prizeResults = results.getPrizeResults();
        assertThat(prizeResults.get(Rank.FIRST)).isEqualTo(1);
        assertThat(prizeResults.get(Rank.THIRD)).isEqualTo(2);
        assertThat(prizeResults.get(Rank.NO_RANK)).isEqualTo(0);
    }

    @Test
    @DisplayName("초기 상태에서 모든 등수의 개수는 0이다")
    void initialState() {
        LottoResults results = new LottoResults();
        HashMap<Rank, Integer> prizeResults = results.getPrizeResults();
        for (Rank rank : Rank.values()) {
            assertThat(prizeResults.get(rank)).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("여러 로또의 결과를 추가하고 조회한다")
    void addMultipleResults() {
        LottoResults results = new LottoResults();
        for (int i = 0; i < 5; i++) {
            Lotto lotto = new Lotto(Arrays.asList(i+1, i+2, i+3, i+4, i+5, i+6));
            results.addResult(lotto, Rank.FIFTH);
        }
        assertThat(results.getPrizeResults().get(Rank.FIFTH)).isEqualTo(5);
    }
}