package lotto.view;

import static lotto.view.constant.OutputMessage.REQUEST_PURCHASE_AMOUNT;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String inputPurchase() {
        System.out.println(REQUEST_PURCHASE_AMOUNT);
        return Console.readLine();
    }
}
