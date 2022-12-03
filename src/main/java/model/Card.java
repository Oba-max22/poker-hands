package model;

public class Card {

    private final String value;

    private final String suit;

    public Card(CardBuilder builder) {
        this.value = builder.value;
        this.suit = builder.suit;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value='" + value + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }

    public static class CardBuilder {

        private String value;

        private String suit;

        public CardBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public CardBuilder setSuit(String suit) {
            this.suit = suit;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
