package lotto.view;

import java.util.List;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;

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
}
