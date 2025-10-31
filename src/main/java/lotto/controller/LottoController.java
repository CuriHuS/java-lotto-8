package lotto.controller;

import lotto.model.domain.Lottos;
import lotto.view.InputView;
import lotto.model.service.LottoService;
import lotto.view.OutputView;

public class LottoController {
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
            throw new IllegalArgumentException("[ERROR] 구매 금액을 입력하세요.");
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
