package lotto;

import java.util.*;

public enum Ranking {
    NONE(0, "0", false),
    FIFTH(3,"5,000",false),
    FOURTH(4,"50,000",false),
    THIRD(5,"1,500,000",false),
    SECOND(5,"30,000,000",true),
    FIRST(6, "2,000,000,000", false);

    private final int correctNumber;
    private final String prizeMoney;
    private final boolean bonusBall;

    Ranking(int correctNumber, String prizeMoney, boolean bonusBall) {
        this.correctNumber = correctNumber;
        this.prizeMoney = prizeMoney;
        this.bonusBall = bonusBall;
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
            if (rank.correctNumber == matchCount && rank.bonusBall && bonusNumberContain) {
                return rank;
            }
            if (rank.correctNumber == matchCount && !rank.bonusBall) {
                return rank;
            }
        }
        return Ranking.NONE;
    }

    public static void printWinningStatics(Map<Ranking, Integer> winningStatics) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Map.Entry<Ranking, Integer> entry : winningStatics.entrySet()) {
            if (entry.getKey().correctNumber != 0) {
                System.out.println(entry.getKey().correctNumber+"개 일치 ("+entry.getKey().prizeMoney+") - "+entry.getValue()+"개");
            }
        }
    }

}
