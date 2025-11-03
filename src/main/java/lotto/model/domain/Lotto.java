package lotto.model.domain;

import java.util.List;
import lotto.model.domain.constant.LottoNumber;

public class Lotto {
    private final List<Integer> numbers;
    private LottoResults result;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateSameNumbers(numbers);
        validateNumberLimit(numbers);
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateSameNumbers(List<Integer> numbers) {
        for (int i = 0; i < numbers.size()-1; i++) {
            if (numbers.get(i) == numbers.get(i+1)) {
                throw new IllegalArgumentException("로또 내 중복된 숫자가 포함될 수 없습니다.");
            }
        }
    }

    private void validateNumberLimit(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > LottoNumber.MAX_LOTTO_NUMBER || numbers.get(i) < LottoNumber.MIN_LOTTO_NUMBER) {
                throw new IllegalArgumentException("로또 범위 내 숫자를 입력해야 합니다.");
            }
        }
    }

    public boolean contains(int number){
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
