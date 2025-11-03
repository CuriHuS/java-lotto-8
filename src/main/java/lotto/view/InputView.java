package lotto.view;

import static lotto.view.constant.OutputMessage.REQUEST_BONUS_NUMBER;
import static lotto.view.constant.OutputMessage.REQUEST_PURCHASE_AMOUNT;
import static lotto.view.constant.OutputMessage.REQUEST_WIN_NUMBERS;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String inputPurchase() {
        System.out.println(REQUEST_PURCHASE_AMOUNT.getMessage());
        return Console.readLine();
    }

    public static String inputWinNumbers() {
        System.out.println(REQUEST_WIN_NUMBERS.getMessage());
        return Console.readLine();
    }

    public static String inputBonusNumber(){
        System.out.println(REQUEST_BONUS_NUMBER.getMessage());
        return Console.readLine();
    }
}
