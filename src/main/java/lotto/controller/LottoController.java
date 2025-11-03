package lotto.controller;

import static lotto.model.domain.constant.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.model.domain.constant.LottoNumber.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.List;
import lotto.model.domain.Lotto;
import lotto.model.domain.LottoResults;
import lotto.model.domain.Lottos;
import lotto.view.InputView;
import lotto.model.service.LottoService;
import lotto.view.OutputView;

public class LottoController {
    private static final String WINNING_NUMBERS_REGEX = ",";
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseAmount = inputPurchaseAmount();
        Lottos lottos = lottoService.issueLottos(purchaseAmount);
        OutputView.printIssuedLottos(lottos);

        Lotto winningLotto = inputWinningLotto();
        int bonusNumber = inputBonusNumber(winningLotto);

        LottoResults lottoResults = lottoService.checkWinning(lottos, winningLotto, bonusNumber);
        OutputView.printLottoResults(lottoResults);
        double returnRate = lottoService.calculateReturnRate(lottoResults, purchaseAmount);
        OutputView.printLottoReturnRate(returnRate);
    }

    private int inputPurchaseAmount() {
        while (true) {
            try {
                String purchaseInput = InputView.inputPurchase();
                int purchaseAmount = parsePurchaseAmount(purchaseInput);
                lottoService.issueLottos(purchaseAmount);
                return purchaseAmount;
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private Lotto inputWinningLotto() {
        while (true) {
            try {
                String winNumbers = InputView.inputWinNumbers();
                return parseWinningLotto(winNumbers);
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private Lotto parseWinningLotto(String winningLottoNumbers) {
        validateNotEmpty(winningLottoNumbers);
        String[] numbers = winningLottoNumbers.split(WINNING_NUMBERS_REGEX);
        List<Integer> winningNumbers = new ArrayList<Integer>();
        for (String s : numbers) {
            int number = parseNumber(s);
            winningNumbers.add(number);
        }
        return new Lotto(winningNumbers);
    }

    private int inputBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                String bonusNumberInput = InputView.inputBonusNumber();
                int bonusNumber = parseBonusNumber(bonusNumberInput);
                validateNumberRange(bonusNumber);
                validateBonusNumberNotInWinningLotto(bonusNumber, winningLotto);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private int parseNumber(String number) {
        validateNotEmpty(number);
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자를 입력해야 합니다.");
        }
    }

    private int parsePurchaseAmount(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private int parseBonusNumber(String bonusNumber) {
        validateNotEmpty(bonusNumber);
        try {
            return Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 숫자를 입력해야 합니다.");
        }
    }

    private void validateNotEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력되지 않았습니다.");
        }
    }

    private void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("구매 금액은 숫자여야 합니다.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateBonusNumberNotInWinningLotto(int bonusNumber, Lotto winningLotto) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
