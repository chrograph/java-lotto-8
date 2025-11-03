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
        Exception.checkPurchaseAmount(purchaseAmount);

        return purchaseAmount;
    }

    private static int getBonusNumber(List<Integer> winningNumber) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        Exception.checkBonusNumber(bonusNumber, winningNumber);
        System.out.println();

        return Integer.parseInt(bonusNumber);
    }

    private static List<Integer> getWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String inputWinningNumber = Console.readLine();
        System.out.println();

        List<Integer> winningNumber = new ArrayList<>();
        for (String number : inputWinningNumber.split(",")) {
            winningNumber.add(Integer.parseInt(number));
        }
        Exception.checkWinningNumber(winningNumber);
        return winningNumber;
    }

    private static int getLottoQuantity(String purchaseAmount) {
        int lottoQuantity = Integer.parseInt(purchaseAmount) / 1000;
        System.out.println(lottoQuantity +"개를 구매했습니다.");
        return lottoQuantity;
    }

}
