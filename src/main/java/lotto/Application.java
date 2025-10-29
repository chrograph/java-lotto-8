package lotto;

import java.util.List;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmount = Console.readLine();
        System.out.println();

        List<Lotto> lottos = Lotto.makeLottos(getLottoQuantity(purchaseAmount));
        Lotto.printLottos(lottos);
    }

    private static int getLottoQuantity(String purchaseAmount) {
        int lottoQuantity = Integer.parseInt(purchaseAmount) / 1000;
        System.out.println(lottoQuantity +"개를 구매했습니다.");
        return lottoQuantity;
    }
}
