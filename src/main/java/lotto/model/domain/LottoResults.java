package lotto.model.domain;

import java.util.HashMap;

public class LottoResults {
    private final HashMap<Lotto, Rank> results;
    private final HashMap<Rank, Integer> prizeResults;

    public LottoResults() {
        this.results = new HashMap<>();
        this.prizeResults = new HashMap<>();
        for (Rank rank : Rank.values()) {
            prizeResults.put(rank, 0);
        }
    }

    public void addResult(Lotto lotto, Rank rank) {
        results.put(lotto, rank);
        prizeResults.put(rank, prizeResults.get(rank) + 1);
    }

    public HashMap<Lotto, Rank> getResults() {
        return results;
    }

    public HashMap<Rank, Integer> getPrizeResults() {
        return prizeResults;
    }
}