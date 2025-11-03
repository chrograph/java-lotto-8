package lotto;

import lotto.Exception;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ExceptionTest {

    @Test
    void checkCommonInput() {
    }

    @Test
    void checkPurchaseAmount() {
    }

    @Test
    @DisplayName("보너스 번호, 당첨 번호 중복 확인")
    void checkBonusNumber() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        String bonusNumber = "6";

        //when&then
        assertThatThrownBy(()->Exception.checkBonusNumber(bonusNumber,winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호랑 중복된 입력입니다.");
    }

    @Test
    @DisplayName("당첨 번호 6개 확인")
    void checkWinningNumber_size() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5);

        //when&then
        assertThatThrownBy(() -> Exception.checkWinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호 중복 확인")
    void checkWinningNumber_duplicate() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 5);

        //when&then
        assertThatThrownBy(() -> Exception.checkWinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 당첨 번호가 입력되었습니다.");
    }
}