package lotto.model.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.domain.constant.LottoNumber;

/**
 * 로또를 생성하는 공장 클래스입니다.
 */
public class LottoFactory {
    /**
     * 랜덤하게 로또 번호를 생성합니다.
     */
    public static Lotto createAutoLotto() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(
                LottoNumber.MIN_LOTTO_NUMBER,
                LottoNumber.MAX_LOTTO_NUMBER,
                LottoNumber.LOTTO_NUMBER_COUNT
        ));
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    /**
     * 여러 장의 자동 로또를 생성합니다.
     */
    public static Lottos createAutoLottos(int count) {
        List<Lotto> lottoList = new ArrayList<Lotto>();
        for (int i = 0; i < count; i++) {
            lottoList.add(createAutoLotto());
        }
        return new Lottos(lottoList);
    }
}