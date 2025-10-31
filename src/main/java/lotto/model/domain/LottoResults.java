package lotto.model.domain;

import java.util.HashMap;

/**
 * 로또 별 등수를 관리하는 클래스입니다.
 */
public class LottoResults {
    private final HashMap<Lotto, Rank> results;
    public LottoResults() {
        this.results = new HashMap<>();
    }

    public void addResult(Lotto lotto, Rank rank) {
        results.put(lotto, rank);
    }

    public HashMap<Lotto, Rank> getResults() {
        return results;
    }
}