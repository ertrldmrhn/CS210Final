package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotal() {
        int total = 0, aces = 0;
        for (Card c : cards) {
            total += c.getValue();
            if (c.getValue() == Rank.ACE.getValue()) aces++;
        }
        // downgrade Aces from 11 to 1
        while (total > 21 && aces-- > 0) {
            total -= 10;
        }
        return total;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getTotal() == 21;
    }

    public boolean isBust() {
        return getTotal() > 21;
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}