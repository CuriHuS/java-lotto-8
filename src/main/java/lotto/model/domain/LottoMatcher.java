package lotto.model.domain;

/**
 * 로또의 번호 일치를 확인하는 클래스입니다.
 */
public class LottoMatcher {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public LottoMatcher(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public int countMatchingNumbers(Lotto lotto) {
        int count = 0;
        for (int number: lotto.getNumbers()){
            if (winningLotto.contains(number)){
                count++;
            }
        }
        return count;
    }

    public boolean hasBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    public Rank determineRank(Lotto lotto) {
        int matchCount =  countMatchingNumbers(lotto);
        boolean matchBonus = hasBonusNumber(lotto);
        if (matchCount < 3) {
            return Rank.NO_RANK;
        }
        if (matchCount == 3) {
            return Rank.FIFTH;
        }
        if (matchCount == 4) {
            return Rank.FOURTH;
        }
        if (matchCount == 5 && matchBonus) {
            return Rank.SECOND;
        }
        if (matchCount == 5) {
            return Rank.THIRD;
        }
        return Rank.FIRST;
    }
}
