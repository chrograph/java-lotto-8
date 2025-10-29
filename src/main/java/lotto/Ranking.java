package lotto;

public enum Ranking {
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
}
