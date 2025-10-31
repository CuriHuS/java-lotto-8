package lotto.controller;

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

    public void run()
    {
        String purchaseInput = InputView.inputPurchase();
        int purchaseAmount = parsePurchaseAmount(purchaseInput);
        Lottos lottos = lottoService.issueLottos(purchaseAmount);
        OutputView.printIssuedLottos(lottos);

        String winNumbers = InputView.inputWinNumbers();
        Lotto winningLotto = parseWinningLotto(winNumbers);
        String bonusNumberInput = InputView.inputBonusNumber();
        int bonusNumber = parseBonusNumber(bonusNumberInput);

        LottoResults lottoResults = lottoService.checkWinning(lottos, winningLotto, bonusNumber);
        OutputView.printLottoResults(lottoResults);
        double returnRate = lottoService.calculateReturnRate(lottoResults, purchaseAmount);
        OutputView.printLottoReturnRate(returnRate);
    }

    private Lotto parseWinningLotto(String winningLottoNumbers){
        validateNotEmpty(winningLottoNumbers);
        String[] numbers = winningLottoNumbers.split(WINNING_NUMBERS_REGEX);
        List<Integer> winningNumbers = new ArrayList<Integer>();
        for (int i = 0; i<numbers.length; i++){
            int number = parseNumber(numbers[i]);
            winningNumbers.add(number);
        }
        return new Lotto(winningNumbers);
    }

    private int parseBonusNumber(String bonusNumber){
        try {
            return Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자를 입력해야 합니다.");
        }
    }

    private int parseNumber(String number){
        validateNotEmpty(number);
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자를 입력해야 합니다.");
        }
    }

    private int parsePurchaseAmount(String input)
    {
        validateNotEmpty(input);
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private void validateNotEmpty(String input)
    {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력되지 않았습니다.");
        }
    }

    private void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 숫자여야 합니다.");
        }
    }
}
