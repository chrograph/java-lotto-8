package lotto;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmount = Console.readLine();
        System.out.println();

        List<Lotto> lottos = Lotto.makeLottos(getLottoQuantity(purchaseAmount));
        Lotto.printLottos(lottos);

        List<Integer> winningNumber = getWinningNumber();

        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());
    }

    private static List<Integer> getWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String InputWinningNumber = Console.readLine();

        List<Integer> winningNumber = new ArrayList<>();
        for (String number : InputWinningNumber.split(",")) {
            winningNumber.add(Integer.parseInt(number));
        }
        return winningNumber;
    }

    private static int getLottoQuantity(String purchaseAmount) {
        int lottoQuantity = Integer.parseInt(purchaseAmount) / 1000;
        System.out.println(lottoQuantity +"개를 구매했습니다.");
        return lottoQuantity;
    }
}
