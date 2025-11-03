package lotto.model.domain;

import java.util.List;

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
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateSameNumbers(List<Integer> numbers) {
        for (int i = 0; i < numbers.size()-1; i++) {
            if (numbers.get(i) == numbers.get(i+1)) {
                throw new IllegalArgumentException("[ERROR] 로또 내 중복된 숫자가 포함될 수 없습니다.");
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
