package lotto.view.constant;

public enum OutputMessage {
    REQUEST_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    REQUEST_WIN_NUMBERS("당첨 번호를 입력해 주세요."),
    REQUEST_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    REPLY_FIFTH_PRIZE("3개 일치 (5,000원) - "),
    REPLY_FOURTH_PRIZE("4개 일치 (50,000원) - "),
    REPLY_THIRD_PRIZE("5개 일치 (1,500,000원) - "),
    REPLY_SECOND_PRIZE("5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    REPLY_FIRST_PRIZE("6개 일치 (2,000,000,000원) - ");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
