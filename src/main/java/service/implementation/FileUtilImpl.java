package service.implementation;

import model.Card;
import service.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtilImpl implements FileUtil {

    private final static String FILE_PATH = "src/main/resources/poker.txt";

    @Override
    public List<List<List<Card>>> readFile() {
        List<List<List<Card>>> hands = new ArrayList<>(2);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            List<List<Card>> hand1List = new ArrayList<>();
            List<List<Card>> hand2List = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strArray = line.split("\\s+");

                List<Card> hand1 = new ArrayList<>(5);
                List<Card> hand2 = new ArrayList<>(5);

                int mid = (strArray.length / 2) - 1;
                for (int i=0; i < strArray.length; i++) {
                    String str = strArray[i];
                    String value = str.split("")[0];
                    String suit = str.split("")[1];
                    if (i <= mid) {
                        hand1.add(new Card.CardBuilder()
                                .setValue(value)
                                .setSuit(suit)
                                .build());
                    } else {
                        hand2.add(new Card.CardBuilder()
                                .setValue(value)
                                .setSuit(suit)
                                .build());
                    }
                }
                hand1List.add(hand1);
                hand2List.add(hand2);
            }

            hands.add(hand1List);
            hands.add(hand2List);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hands;
    }
}
