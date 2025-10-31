package lotto.model.domain;

import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    /**
     * 로또 개수를 반환합니다.
     */
    public int size() {
        return lottos.size();
    }

    /**
     * 특정 인덱스의 로또를 반환합니다.
     */
    public Lotto get(int index) {
        return lottos.get(index);
    }

    /**
     * 로또 목록을 반환합니다.
     */
    public List<Lotto> getLottos() {
        return lottos;
    }
}