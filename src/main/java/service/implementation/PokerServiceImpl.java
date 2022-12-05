package service.implementation;

import enums.Rank;
import model.Card;
import model.Player;
import service.PokerService;
import utility.CardUtil;
import utility.RankUtil;

import java.util.*;

public class PokerServiceImpl implements PokerService {

    private static final Map<Rank, Integer> RANK_MAP = RankUtil.getRankMap();
    private static final Map<String, Integer> CARD_MAP = CardUtil.getCardMap();

    @Override
    public Player determineWinner(Player playerOne, Player playerTwo) {
        Rank playerOneRank = determineRank(playerOne.getHand());
        Rank playerTwoRank = determineRank(playerTwo.getHand());

        if (playerOneRank.equals(playerTwoRank)) {
            if (playerOneRank == Rank.ONE_PAIR
            || playerOneRank == Rank.TWO_PAIRS
            || playerOneRank == Rank.THREE_OF_A_KIND
            || playerOneRank == Rank.FOUR_OF_A_KIND) {
                int pairOneValue = getPairValue(playerOne.getHand());
                int pairTwoValue = getPairValue(playerTwo.getHand());
                return pairOneValue > pairTwoValue ? playerOne : playerTwo;
            } else {
                String highestPlayerOneCard = getHighestCard(playerOne.getHand());
                String highestPlayerTwoCard = getHighestCard(playerTwo.getHand());

                return CARD_MAP.get(highestPlayerOneCard) > CARD_MAP.get(highestPlayerTwoCard) ? playerOne : playerTwo;
            }
        } else {
            return RANK_MAP.get(playerOneRank) > RANK_MAP.get(playerTwoRank) ? playerOne : playerTwo;
        }
    }

    private Rank determineRank(List<Card> hand) {
        Rank result;
        if (isRoyalFlush(hand)) {
            result = Rank.ROYAL_FLUSH;
        } else if (isStraightFlush(hand)) {
            result = Rank.STRAIGHT_FLUSH;

        } else if (isFourOfAKind(hand)) {
            result = Rank.FOUR_OF_A_KIND;

        } else if (isFullHouse(hand)) {
            result = Rank.FULL_HOUSE;

        } else if (isFlush(hand)) {
            result = Rank.FLUSH;

        } else if (isStraight(hand)) {
            result = Rank.STRAIGHT;

        } else if (isThreeOfAKind(hand)) {
            result = Rank.THREE_OF_A_KIND;

        } else if (hasTwoPairs(hand)) {
            result = Rank.TWO_PAIRS;

        } else if (hasOnePair(hand)) {
            result = Rank.ONE_PAIR;
        } else {
            result = Rank.HIGH_CARD;
        }
        return result;
    }

    private int getPairValue(List<Card> hand) {
        Map<String, Integer> frequencyMap = getFrequencyMap(hand);
        int maxFreq = 0;
        int pairValue = 0;

        for (Card card: hand) {
            int frequency = frequencyMap.get(card.getValue());
            int currCardVal = CARD_MAP.get(card.getValue());

            if (frequency > maxFreq) {
                maxFreq = frequency;
                pairValue = currCardVal;
            }
        }

        return pairValue;
    }

    public String getHighestCard(List<Card> hand) {
        String maxCard = "2";
        int maxCardVal = 1;

        for (Card card: hand) {
            int currCardVal = CARD_MAP.get(card.getValue());
            if (currCardVal > maxCardVal) {
                maxCardVal = currCardVal;
                maxCard = card.getValue();
            }
        }

        return maxCard;
    }

    public Map<String, Integer> getFrequencyMap(List<Card> hand) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (Card card : hand) {
            String val = card.getValue();
            frequencyMap.put(val, frequencyMap.getOrDefault(val, 0) + 1);
        }
        return frequencyMap;
    }


    private boolean hasOnePair(List<Card> hand) {
        Map<String, Integer> frequencyMap = getFrequencyMap(hand);
        return frequencyMap.containsValue(2);
    }

    private boolean hasTwoPairs(List<Card> hand) {
        Map<String, Integer> frequencyMap = getFrequencyMap(hand);
        return Collections.frequency(frequencyMap.values(), 2) == 2;
    }

    private boolean isThreeOfAKind(List<Card> hand) {
        Map<String, Integer> frequencyMap = getFrequencyMap(hand);
        return frequencyMap.containsValue(3);
    }

    private boolean isStraight(List<Card> hand) {
        String[] defaultValues = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        List<String> valueOrderList = new ArrayList<>(Arrays.asList(defaultValues));

        List<String> cardList = new ArrayList<>();
        for (Card card: hand) {
            String val = card.getValue();
            cardList.add(val);
        }

        Collections.sort(cardList);

        int windowStart = 0;
        int windowEnd = hand.size()-1;

        while(windowEnd < valueOrderList.size()) {
            List<String> windowList = new ArrayList<>();

            for (int i = windowStart; i <= windowEnd; i++) {
                windowList.add(valueOrderList.get(i));
            }

            if (windowList.equals(cardList)) {
                return true;
            }
            windowStart++;
            windowEnd++;
        }

        return false;
    }

    private boolean isFlush(List<Card> hand) {
        String mainSuit = hand.get(0).getSuit();
        Map<String, Integer> suitFreqMap = new HashMap<>();
        for (Card card : hand) {
            String suit = card.getSuit();
            suitFreqMap.put(suit, suitFreqMap.getOrDefault(suit, 0) + 1);
        }

        return suitFreqMap.get(mainSuit) == 5;
    }

    private boolean isFullHouse(List<Card> hand) {
        Map<String, Integer> frequencyMap = getFrequencyMap(hand);
        Set<Integer> fullHouseSet = new HashSet<>(frequencyMap.values());
        return fullHouseSet.contains(2) && fullHouseSet.contains(3);
    }

    private boolean isFourOfAKind(List<Card> hand) {
        Map<String, Integer> frequencyMap = getFrequencyMap(hand);
        return frequencyMap.containsValue(4);
    }

    private boolean isStraightFlush(List<Card> hand) {
        return isFlush(hand) && isStraight(hand);
    }

    private boolean isRoyalFlush(List<Card> hand) {
        Set<String> values = new HashSet<>();
        List<String> royalFlushValues = new ArrayList<>(Arrays.asList("T", "J", "Q", "K", "A"));

        if (isFlush(hand)) {
            for (Card card : hand) {
                String val = card.getValue();
                values.add(val);
            }
        }

        return values.containsAll(royalFlushValues);
    }
}
