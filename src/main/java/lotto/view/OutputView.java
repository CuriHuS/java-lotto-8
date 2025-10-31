package lotto.view;

import static lotto.model.domain.Rank.FIFTH;
import static lotto.model.domain.Rank.FIRST;
import static lotto.model.domain.Rank.FOURTH;
import static lotto.model.domain.Rank.SECOND;
import static lotto.model.domain.Rank.THIRD;
import static lotto.view.constant.OutputMessage.REPLY_FIFTH_PRIZE;
import static lotto.view.constant.OutputMessage.REPLY_FIRST_PRIZE;
import static lotto.view.constant.OutputMessage.REPLY_FOURTH_PRIZE;
import static lotto.view.constant.OutputMessage.REPLY_THIRD_PRIZE;

import java.util.HashMap;
import java.util.List;
import lotto.model.domain.Lotto;
import lotto.model.domain.LottoResults;
import lotto.model.domain.Lottos;
import lotto.model.domain.Rank;

public class OutputView {
    public static void printIssuedLottos(Lottos lottos) {
        System.out.println(lottos.size()+"개를 구매했습니다.");
        for (int i=0; i<lottos.size(); i++) {
            Lotto lotto = lottos.get(i);
            printLotto(lotto);
        }
    }

    /**
     * 개별 로또 번호를 출력합니다.
     */
    public static void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        System.out.println(numbers);
    }

    public static void printLottoResults(LottoResults lottoResults) {
        HashMap<Rank, Integer> rankCounts = lottoResults.getPrizeResults();
        System.out.println(REPLY_FIFTH_PRIZE.getMessage() + rankCounts.get(FIFTH) + "개");
        System.out.println(REPLY_FOURTH_PRIZE.getMessage() + rankCounts.get(FOURTH) + "개");
        System.out.println(REPLY_THIRD_PRIZE.getMessage() + rankCounts.get(THIRD) + "개");
        System.out.println(REPLY_FIFTH_PRIZE.getMessage() + rankCounts.get(SECOND) + "개");
        System.out.println(REPLY_FIRST_PRIZE.getMessage() + rankCounts.get(FIRST) + "개");
    }
}
