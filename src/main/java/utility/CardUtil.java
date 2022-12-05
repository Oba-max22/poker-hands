package utility;

import java.util.HashMap;
import java.util.Map;

public class CardUtil {

    public static Map<String, Integer> getCardMap() {
        Map<String, Integer> cardMap = new HashMap<>();
        String[] defaultCards = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        for (int i=0; i < defaultCards.length; i++) {
            cardMap.put(defaultCards[i], i+1);
        }

        return cardMap;
    }
}
