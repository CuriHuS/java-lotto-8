package lotto.model.domain.constant;

public enum ExceptionMessage {
    ERROR("[ERROR]");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
