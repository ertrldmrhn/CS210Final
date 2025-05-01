package BlackJack;

import java.util.*;

public class Hand {
	private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotal() {
        int total = 0;
        int aces = 0;
        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aces++;
            }
        }
        // Handle Aces as 11 or 1
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
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
        return cards;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
        }
        return sb.toString();
    }
    
}

