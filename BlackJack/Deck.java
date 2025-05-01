package BlackJack;

import java.util.*;

public class Deck {
	private List<Card> cards;
    private static final int SHUFFLE_THRESHOLD = 26; // half of 52

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("\n-- Deck shuffled --");
    }

    public Card drawCard() {
        if (cards.size() <= SHUFFLE_THRESHOLD) {
            System.out.println("\n-- Low cards, reshuffling deck --");
            cards.clear();
            initializeDeck();
            shuffle();
        }

        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // Deck should never be empty because of auto reshuffle
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
