package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionTest {
    @Test
    @DisplayName("구매 금액이 빈 문자열이면 예외 발생")
    void checkPurchaseAmount_noneInput() {
        //given
        String purchaseAmount = "";

        //when&then
        assertThatThrownBy(() -> Exception.checkPurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 값을 입력하지 않았습니다.");
    }
    @Test
    @DisplayName("구매 금액이 1000원 미만이면 예외 발생")
    void checkPurchaseAmount_lessAmount() {
        //given
        String purchaseAmount = "900";

        //when&then
        assertThatThrownBy(() -> Exception.checkPurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액이 1000원 미만입니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복이면 예외 발생")
    void checkBonusNumber_duplicate_with_winningNumber() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        String bonusNumber = "6";

        //when&then
        assertThatThrownBy(()->Exception.checkBonusNumber(bonusNumber,winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호랑 중복된 입력입니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외 발생")
    void checkWinningNumber_notSix() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5);

        //when&then
        assertThatThrownBy(() -> Exception.checkWinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 예외 발생")
    void checkWinningNumber_duplicate() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 5);

        //when&then
        assertThatThrownBy(() -> Exception.checkWinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 당첨 번호가 입력되었습니다.");
    }
}