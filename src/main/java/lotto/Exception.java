package lotto;

import java.util.List;

public class Exception {
    public static void checkCommonInput(String inputValue) {
        if (inputValue == null || inputValue.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 값을 입력하지 않았습니다.");
        }
        if (!inputValue.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 문자가 입력되었습니다.");
        }
    }

    public static void checkPurchaseAmount(String purchaseAmount) {
        checkCommonInput(purchaseAmount);
        if ((Integer.parseInt(purchaseAmount) / 1000) == 0) {
            throw new IllegalArgumentException("[ERROR] 금액이 1000원 미만입니다.");
        }
    }

    public static void checkBonusNumber(String bonusNumber, List<Integer> winningNumber) {
        checkCommonInput(bonusNumber);
        if (winningNumber.contains(Integer.parseInt(bonusNumber))) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호랑 중복된 입력입니다.");
        }
    }

    public static void checkWinningNumber(List<Integer> winningNumber) {
        if (winningNumber.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if (winningNumber.stream().distinct().count() != winningNumber.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 당첨 번호가 입력되었습니다.");
        }
    }

}
