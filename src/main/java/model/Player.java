package model;

import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(PlayerBuilder playerBuilder) {
        this.name = playerBuilder.name;
        this.hand = playerBuilder.hand;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                '}';
    }

    public static class PlayerBuilder {
        private String name;
        private List<Card> hand;

        public PlayerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder setHand(List<Card> hand) {
            this.hand = hand;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
