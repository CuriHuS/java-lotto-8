package lotto.model.service;

import lotto.model.domain.*;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;

    /**
     * 구매 금액에 따라 로또를 발행합니다.
     */
    public Lottos issueLottos (int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int lottoCount = calculateLottoCount(purchaseAmount);
        return LottoFactory.createAutoLottos(lottoCount);
    }

    /**
     * 발행된 로또들의 당첨 결과를 확인합니다.
     */
    public LottoResults checkWinning(Lottos lottos, Lotto winningLotto, int bonusNumber) {
        LottoMatcher matcher = new LottoMatcher(winningLotto, bonusNumber);
        LottoResults results = new LottoResults();

        for (int i=0; i<lottos.size(); i++){
            Lotto lotto  = lottos.get(i);
            Rank rank = matcher.determineRank(lotto);
            results.addResult(lotto, rank);
        }

        return results;
    }

    /**
     * 수익률을 계산합니다.
     */
    public double calculateReturnRate(LottoResults results, int purchaseAmount) {
        long totalPrize = calculateTotalPrize(results);
        double rate = (double) totalPrize / purchaseAmount;
        return Math.round(rate * 1000) / 10.0;
    }

    /**
     * 총 당첨 금액을 계산합니다.
     */
    private long calculateTotalPrize(LottoResults results) {
        long total = 0;
        for (Rank rank : results.getResults().values()) {
            total += rank.getPrize();
        }
        return total;
    }

    private int calculateLottoCount(int amount)
    {
        return amount / LOTTO_PRICE;
    }

    private void validatePurchaseAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1,000원 이상이어야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1,000원 단위로 떨어져야 합니다");
        }
    }
}
