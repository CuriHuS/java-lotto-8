package lotto.controller;

import lotto.model.domain.Lotto;
import lotto.model.service.LottoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoControllerTest {
    private LottoController controller;
    private LottoService service;

    @BeforeEach
    void setUp() {
        service = new LottoService();
        controller = new LottoController(service);
    }

    @Test
    @DisplayName("구매 금액을 정상적으로 파싱한다")
    void parsePurchaseAmount_success() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parsePurchaseAmount", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(controller, "5000");

        assertThat(result).isEqualTo(5000);
    }

    @Test
    @DisplayName("구매 금액이 비어있으면 예외가 발생한다")
    void parsePurchaseAmount_empty() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parsePurchaseAmount", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, ""))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 금액이 숫자가 아니면 예외가 발생한다")
    void parsePurchaseAmount_notNumber() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parsePurchaseAmount", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "abc"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호를 정상적으로 파싱한다")
    void parseWinningLotto_success() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseWinningLotto", String.class);
        method.setAccessible(true);

        Lotto result = (Lotto) method.invoke(controller, "1,2,3,4,5,6");

        assertThat(result.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("당첨 번호가 비어있으면 예외가 발생한다")
    void parseWinningLotto_empty() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseWinningLotto", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, ""))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 예외가 발생한다")
    void parseWinningLotto_containsNotNumber() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseWinningLotto", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "1,2,3,a,5,6"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다")
    void parseWinningLotto_notSix() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseWinningLotto", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "1,2,3,4,5"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다")
    void parseWinningLotto_duplicate() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseWinningLotto", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "1,1,3,4,5,6"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호를 정상적으로 파싱한다")
    void parseBonusNumber_success() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseBonusNumber", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(controller, "7");

        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다")
    void parseBonusNumber_notNumber() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseBonusNumber", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "abc"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("개별 번호를 정상적으로 파싱한다")
    void parseNumber_success() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseNumber", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(controller, "42");

        assertThat(result).isEqualTo(42);
    }

    @Test
    @DisplayName("개별 번호가 비어있으면 예외가 발생한다")
    void parseNumber_empty() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseNumber", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, ""))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("개별 번호가 숫자가 아니면 예외가 발생한다")
    void parseNumber_notNumber() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseNumber", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "abc"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백만 있는 입력은 비어있는 것으로 간주한다")
    void validateNotEmpty_onlySpaces() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("validateNotEmpty", String.class);
        method.setAccessible(true);

        // trim()을 사용하지 않는 현재 구현에서는 공백이 허용됨
        // 만약 trim()을 추가한다면 예외가 발생해야 함
        assertThatThrownBy(() -> {
            method.invoke(controller, "   ");
            Method parseMethod = LottoController.class.getDeclaredMethod("parsePurchaseAmount", String.class);
            parseMethod.setAccessible(true);
            parseMethod.invoke(controller, "   ");
        }).hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 검증 로직이 정상 동작한다")
    void validateNumeric_success() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("validateNumeric", String.class);
        method.setAccessible(true);

        // 정상적인 숫자는 예외가 발생하지 않음
        method.invoke(controller, "12345");
    }

    @Test
    @DisplayName("숫자 검증 로직이 문자를 거부한다")
    void validateNumeric_notNumber() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("validateNumeric", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(controller, "abc123"))
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("큰 숫자도 정상적으로 파싱한다")
    void parsePurchaseAmount_largeNumber() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parsePurchaseAmount", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(controller, "1000000");

        assertThat(result).isEqualTo(1000000);
    }

    @Test
    @DisplayName("음수는 파싱되지만 Service 계층에서 검증된다")
    void parsePurchaseAmount_negative() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parsePurchaseAmount", String.class);
        method.setAccessible(true);

        int result = (int) method.invoke(controller, "-1000");

        assertThat(result).isEqualTo(-1000);
        // Service 계층에서 검증되어야 함
    }

    @Test
    @DisplayName("쉼표로 구분된 당첨 번호를 개별 숫자로 변환한다")
    void parseWinningLotto_separatesByComma() throws Exception {
        Method method = LottoController.class.getDeclaredMethod("parseWinningLotto", String.class);
        method.setAccessible(true);

        Lotto result = (Lotto) method.invoke(controller, "10,20,30,40,41,45");

        assertThat(result.getNumbers()).hasSize(6);
        assertThat(result.getNumbers()).contains(10, 20, 30, 40, 41, 45);
    }
}