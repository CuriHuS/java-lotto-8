package lotto.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {
    @Test
    @DisplayName("자동 로또를 생성한다.")
    void createAutoLotto() {
        Lotto lotto = LottoFactory.createAutoLotto();

        assertThat(lotto).isNotNull();
        assertThat(lotto.getNumbers()).hasSize(6);
        assertThat(lotto.getNumbers()).allMatch(num -> num >= 1 && num <= 45);
    }

    @Test
    @DisplayName("자동 로또의 번호는 정렬되어 있다.")
    void createAutoLotto_sorted() {
        Lotto lotto = LottoFactory.createAutoLotto();

        assertThat(lotto.getNumbers()).isSorted();
    }

    @Test
    @DisplayName("여러 장의 자동 로또를 생성한다.")
    void createAutoLottos() {
        int count = 5;
        Lottos lottos = LottoFactory.createAutoLottos(count);

        assertThat(lottos).isNotNull();
        assertThat(lottos.size()).isEqualTo(count);
    }

    @Test
    @DisplayName("0장의 로또를 생성한다.")
    void createAutoLottos_zero() {
        Lottos lottos = LottoFactory.createAutoLottos(0);

        assertThat(lottos.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("생성된 각 로또는 중복되지 않은 번호를 가진다.")
    void createAutoLotto_unique() {
        Lotto lotto = LottoFactory.createAutoLotto();

        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }
}