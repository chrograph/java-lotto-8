package lotto;

import java.util.*;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        try{
            run();
        }catch(IllegalArgumentException error){
            System.out.println(error.getMessage());
        }
    }

    public static void run() {
        String purchaseAmount = getPurchaseAmount();

        List<Lotto> lottos = Lotto.makeLottos(getLottoQuantity(purchaseAmount));
        Lotto.printLottos(lottos);

        List<Integer> winningNumber = getWinningNumber();

        int bonusNumber = getBonusNumber(winningNumber);

        Map<Ranking, Integer> winningStatics = Ranking.getWinningStatics(lottos, winningNumber, bonusNumber);
        Ranking.printWinningStatics(winningStatics);

        double profitRate = Ranking.getProfitRate(winningStatics, purchaseAmount) * 100;
        System.out.println("총 수익률은 "+ String.format("%.1f",profitRate) +"%입니다.");
   }

    private static String getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmount = Console.readLine();
        System.out.println();
        checkPurchaseAmountException(purchaseAmount);

        return purchaseAmount;
    }

    private static void commonInputException(String inputValue) {
        if (inputValue == null || inputValue.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 값을 입력하지 않았습니다.");
        }
        if (!inputValue.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 문자가 입력되었습니다.");
        }
    }

    private static void checkPurchaseAmountException(String purchaseAmount) {
        if ((Integer.parseInt(purchaseAmount) / 1000) == 0) {
            throw new IllegalArgumentException("[ERROR] 금액이 1000원 미만입니다.");
        }
        commonInputException(purchaseAmount);
    }

    private static int getBonusNumber(List<Integer> winningNumber) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        checkBonusNumberException(bonusNumber, winningNumber);
        System.out.println();

        return Integer.parseInt(bonusNumber);
    }

    private static void checkBonusNumberException(String bonusNumber, List<Integer> winningNumber) {
        commonInputException(bonusNumber);
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호랑 중복된 입력입니다.");
        }
    }

    private static List<Integer> getWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String InputWinningNumber = Console.readLine();
        System.out.println();

        List<Integer> winningNumber = new ArrayList<>();
        for (String number : InputWinningNumber.split(",")) {
            winningNumber.add(Integer.parseInt(number));
        }

        return winningNumber;
    }

    private static void checkWinningNumberException(List<Integer> winningNumber) {
        if (winningNumber.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if (winningNumber.stream().distinct().count() != 0) {
            throw new IllegalArgumentException("[ERROR] 중복된 당첨 번호가 입력되었습니다.");
        }
        commonInputException(winningNumber.toString());
    }

    private static int getLottoQuantity(String purchaseAmount) {
        int lottoQuantity = Integer.parseInt(purchaseAmount) / 1000;
        System.out.println(lottoQuantity +"개를 구매했습니다.");
        return lottoQuantity;
    }

}
