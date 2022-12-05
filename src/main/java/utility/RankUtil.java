package utility;

import enums.Rank;

import java.util.HashMap;
import java.util.Map;

import static enums.Rank.*;

public class RankUtil {

    public static Map<Rank, Integer> getRankMap() {
        Map<Rank, Integer> rankMap = new HashMap<>();

        for (Rank rank: Rank.values()) {
            switch (rank) {
                case ROYAL_FLUSH:
                    rankMap.put(ROYAL_FLUSH, 100);
                    break;
                case STRAIGHT_FLUSH:
                    rankMap.put(STRAIGHT_FLUSH, 90);
                    break;
                case FOUR_OF_A_KIND:
                    rankMap.put(FOUR_OF_A_KIND, 80);
                    break;
                case FULL_HOUSE:
                    rankMap.put(FULL_HOUSE, 70);
                    break;
                case FLUSH:
                    rankMap.put(FLUSH, 60);
                    break;
                case STRAIGHT:
                    rankMap.put(STRAIGHT, 50);
                    break;
                case THREE_OF_A_KIND:
                    rankMap.put(THREE_OF_A_KIND, 40);
                    break;
                case TWO_PAIRS:
                    rankMap.put(TWO_PAIRS, 30);
                    break;
                case ONE_PAIR:
                    rankMap.put(ONE_PAIR, 20);
                    break;
                default:
                    rankMap.put(HIGH_CARD, 10);
                    break;
            }
        }

        return rankMap;
    }
}
