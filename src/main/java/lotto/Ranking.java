package lotto;

import java.util.*;

public enum Ranking {
    NONE(0, "0"),
    FIFTH(3,"5,000"),
    FOURTH(4,"50,000"),
    THIRD(5,"1,500,000"),
    SECOND(5,"30,000,000"),
    FIRST(6, "2,000,000,000");

    private final int correctNumber;
    private final String prizeMoney;

    Ranking(int correctNumber, String prizeMoney) {
        this.correctNumber = correctNumber;
        this.prizeMoney = prizeMoney;
    }

    public static Map<Ranking, Integer> getWinningStatics(List<Lotto> lottos, List<Integer> winningNumber, int bonusNumber) {
        Map<Ranking, Integer> winningStatics = new EnumMap<>(Ranking.class);
        for (Ranking rank : values()) {
            winningStatics.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            long matchCount = lotto.getNumbers().stream().
                    filter(winningNumber::contains)
                    .count();
            Ranking rank = matchRanks(matchCount, (lotto.getNumbers().contains(bonusNumber)));
            winningStatics.put(rank, winningStatics.get(rank) + 1);
        }
        return winningStatics;
    }

    public static Ranking matchRanks(long matchCount,boolean bonusNumberContain) {
        for (Ranking rank : values()) {
            if (matchCount == rank.correctNumber && matchCount == 5) {
                return distributeSecond(bonusNumberContain);
            }
            if (matchCount == rank.correctNumber) {
                return rank;
            }
        }
        return Ranking.NONE;
    }

    private static Ranking distributeSecond(boolean bonusNumberContain) {
        if (bonusNumberContain == true) {
            return Ranking.SECOND;
        }
        if (bonusNumberContain == false) {
            return Ranking.THIRD;
        }
        throw new IllegalArgumentException();
    }

    public static void printWinningStatics(Map<Ranking, Integer> winningStatics) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Map.Entry<Ranking, Integer> entry : winningStatics.entrySet()) {
            if (entry.getKey().correctNumber != 0 && entry.getKey() != Ranking.SECOND) {
                System.out.println(entry.getKey().correctNumber + "개 일치 (" + entry.getKey().prizeMoney + "원) - " + entry.getValue() + "개");
            }
            if (entry.getKey() == Ranking.SECOND) {
                System.out.println(entry.getKey().correctNumber + "개 일치, 보너스 볼 일치 (" + entry.getKey().prizeMoney + "원) - " + entry.getValue() + "개");
            }
        }
    }

    public static double getProfitRate(Map<Ranking, Integer> winningStatics, String purchaseAmount) {
        double profit = 0.0;
        for (Map.Entry<Ranking, Integer> entry : winningStatics.entrySet()) {
            profit += Integer.parseInt((entry.getKey().prizeMoney.replace(",",""))) * entry.getValue();
        }
        return (profit / Integer.parseInt(purchaseAmount));
    }

}
