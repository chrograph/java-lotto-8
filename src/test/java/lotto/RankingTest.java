package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class RankingTest {

    @Test
    void getWinningStatics() {
    }

    @Test
    @DisplayName("5개 일치 + 보너스 번호 일치 시 SECOND 반환")
    void matchRanks() {
        // given
        long matchCount = 5;
        Boolean bonusNumberContains = true;

        //when
        Ranking result = Ranking.matchRanks(matchCount, bonusNumberContains);

        //then
        assertThat(result).isEqualTo(Ranking.SECOND);
    }

    @Test
    void printWinningStatics() {
    }
}